package com.example.kinofan.ui.data.api

import com.example.kinofan.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object TheMovieDBClient {

    fun getClient(): TheMovieDBInterface {

        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()


            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)
    }
}