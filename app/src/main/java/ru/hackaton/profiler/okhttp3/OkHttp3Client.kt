package ru.hackaton.profiler.okhttp3

import okhttp3.OkHttpClient


class OkHttp3Client {
    private val client = OkHttpClient.Builder()
        .addInterceptor(ProfilerInterceptor())

    fun getClient(): OkHttpClient.Builder {
        return client
    }
}