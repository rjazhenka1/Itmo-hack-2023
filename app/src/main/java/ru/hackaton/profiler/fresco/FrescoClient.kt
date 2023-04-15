package ru.hackaton.profiler.fresco

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import okhttp3.OkHttpClient


class FrescoClient {
    fun getClient(context: Context) {
        val listeners: MutableSet<RequestListener> = HashSet()
        listeners.add(ProfilerRequestListener())
        val config = ImagePipelineConfig.newBuilder(context)
            .setRequestListeners(listeners)
            .build()
        Fresco.initialize(context, config)
    }
}