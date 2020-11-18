package com.catchpig.kotlin_mvp.network

data class Result<T>(val errorCode:Int,val errorMsg:String,val data:T)