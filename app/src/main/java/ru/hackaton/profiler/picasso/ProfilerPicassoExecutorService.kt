package ru.hackaton.profiler.picasso

import com.squareup.picasso.Downloader
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import ru.hackaton.profiler.okhttp3.ProfilerInterceptor
import java.io.IOException

class ProfilerPicassoExecutorService : Downloader {
    private val client = OkHttpClient.Builder()
        .addInterceptor(ProfilerInterceptor())
        .build()

    override fun load(request: Request): Response {
        println("request")
        return client
            .newCall(request)
            .execute()
    }

    override fun shutdown() {
        try {
            client.cache?.close()
        } catch (ignored: IOException) {
        }
    }

}