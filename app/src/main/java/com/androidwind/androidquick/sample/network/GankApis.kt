package com.androidwind.androidquick.sample.network

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
interface GankApis {
    @GET("day/history")
    fun getHistoryDate(): Observable<GankRes<List<String>>>

    @GET("https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg")
    fun getSdkVersion(): Observable<String>

    @GET("day/history")
    fun getHistoryDateAsync(): Deferred<GankRes<List<String>>>

    @GET("day/h")
    fun getError(): Observable<GankRes<List<String>>>
}