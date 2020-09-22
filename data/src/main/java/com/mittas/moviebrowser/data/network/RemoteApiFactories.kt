package com.mittas.moviebrowser.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mittas.moviebrowser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteApiFactories {

    private const val TIMEOUT_SECS = 60L

    fun <T> createApi(baseUrl: String, api: Class<T>): T {
        return createRetrofitApi(baseUrl, api)
    }

    private fun <T> createRetrofitApi(baseUrl: String, api: Class<T>): T {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .build()
            .create(api)
    }

    private fun createGson(): Gson {
        return GsonBuilder().create()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            writeTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }
}