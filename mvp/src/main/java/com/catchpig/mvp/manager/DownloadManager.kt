package com.catchpig.mvp.manager

import android.os.Environment
import com.catchpig.mvp.bean.DownloadInfo
import com.catchpig.mvp.di.NAMED_DOWNLOAD
import com.catchpig.mvp.ext.io2main
import com.catchpig.mvp.listener.DownloadCallback
import com.catchpig.mvp.network.download.DownloadService
import com.catchpig.mvp.network.download.DownloadSubscriber
import com.catchpig.mvp.provider.KotlinMvpContentProvider
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.net.URL
import java.nio.channels.FileChannel
import java.util.concurrent.Flow

/**
 * 描述:下载工具类
  * @author catchpig
 * @date 2020/11/20 10:25
 */
class DownloadManager {
    private var downloadService: DownloadService? = null
    /**
     * 下载
     * @param downloadInfo DownloadInfo 下载信息
     * @param downloadCallback DownLoadCallback 下载回调接口,回调的方法已经切到主线程
     * @return Disposable
     */
    fun download(downloadInfo: DownloadInfo, downloadCallback: DownloadCallback):Disposable{
        val downloadSubscriber = DownloadSubscriber(downloadCallback)
        if (downloadService==null) {
            downloadService = KoinJavaComponent.getKoin().get(named(NAMED_DOWNLOAD)) { parametersOf(downloadInfo.baseUrl,downloadInfo.connectTimeout, downloadSubscriber) }
        }
        val downloadUrl = "${downloadInfo.baseUrl}${downloadInfo.url}"
        val localFilePath = localFileName(downloadUrl)
        Flowable.just(downloadUrl).flatMap {
            /**
             * 判断本地是否存在下载的文件,
             * 如果存在,文件的大小和远程文件大小一样,直接返回本地文件的路劲给回调,
             * 如果不存在,远程下载文件
             */
            val file = File(localFilePath)
            if (file.exists()) {
                val fileLength = file.length()
                val contentLength = URL(it).openConnection().contentLength
                if(fileLength==contentLength.toLong()){
                    downloadSubscriber.update(fileLength,fileLength,true)
                    return@flatMap Flowable.just(localFilePath)
                }
            }
            return@flatMap httpDownload(downloadInfo.url,localFilePath)
        }.io2main().subscribeWith(downloadSubscriber)
        return downloadSubscriber
    }
    private fun httpDownload(url:String,localFilePath:String):Flowable<String>{
        return downloadService!!.download(url).subscribeOn(Schedulers.io()).map {
            return@map writeCache(it, localFilePath)
        }
    }

    /**
     * 生成文件的地址
     * @param downloadUrl String
     * @return String
     */
    private fun localFileName(downloadUrl:String):String{
        val fileName = downloadUrl.replace("/","").replace("\\","")
        var cashDir = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            //有SD卡,拿到SD卡的/storage/sdcard0/Android/data/包名/cash目录
            KotlinMvpContentProvider.application.externalCacheDir!!.absolutePath
        }else{
            //没有SD卡的,拿到/data/data/包名/cash目录
            KotlinMvpContentProvider.application.cacheDir.absolutePath
        }
        return "$cashDir/download/$fileName"
    }

    /**
     * 将下载的数据写入本地缓存
     * @param responseBody ResponseBody 下载的文件数据
     * @param fileName String 文件名称
     * @return String 本地路径
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun writeCache(responseBody: ResponseBody, fileName: String):String{
        var file = File(fileName)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }else if(file.exists()){
            if(file.length()==responseBody.contentLength()){
                return fileName
            }else{
                file.delete()
            }
        }
        var randomAccessFile = RandomAccessFile(file, "rwd")
        var fileChannel = randomAccessFile.channel
        var mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, responseBody.contentLength())
        var buffer = ByteArray(1024 * 8)
        var len: Int
        while (true){
            len = responseBody.byteStream().read(buffer)
            if (len==-1) {
                break
            }
            mappedByteBuffer.put(buffer,0,len)
        }
        responseBody.byteStream().close()
        fileChannel?.close()
        randomAccessFile?.close()
        return fileName
    }
}