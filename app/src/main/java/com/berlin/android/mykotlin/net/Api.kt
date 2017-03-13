package com.berlin.android.mykotlin.net

import com.berlin.android.mykotlin.pojo.PictureJokeBean
import com.berlin.android.mykotlin.pojo.TextJokeBean

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by zhongbolin on 2017/3/10.
 */

interface Api {
    @Headers("Authorization:APPCODE 85d090d3ad48411ba86adc4e4eb1ea68")
    @GET("textJoke")
    fun getTextJokes( @Query("maxResult") pageSize: String, @Query("page") page: String): Call<TextJokeBean>

    @Headers("Authorization:APPCODE 85d090d3ad48411ba86adc4e4eb1ea68")
    @GET("picJoke")
    fun getPicJokes( @Query("maxResult") pageSize: String, @Query("page") page: String) : Call<PictureJokeBean>
}
