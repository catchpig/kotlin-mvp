package com.catchpig.utils.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 *
 * @author TLi2
 **/

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun Context.longToast(content: String) {
    toast(content, Toast.LENGTH_LONG)
}

fun Context.longToast(@StringRes id: Int) {
    toast(id, Toast.LENGTH_LONG)
}

fun String.toast(context: Context){
    context.toast(this)
}

fun String.longToast(context: Context){
    context.longToast(this)
}