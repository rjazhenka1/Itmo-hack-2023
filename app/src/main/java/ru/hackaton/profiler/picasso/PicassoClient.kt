package ru.hackaton.profiler.picasso

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso

class PicassoClient {
    fun getPicasso(context: Context) {
        val builder = Picasso.Builder(context)
        builder.downloader(ProfilerPicassoExecutorService())
        Picasso.setSingletonInstance(builder.build())
    }
}