package ru.hackaton.profiler.fresco

import com.facebook.imagepipeline.listener.BaseRequestListener
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.request.ImageRequest

class ProfilerRequestListener : BaseRequestListener() {
    override fun onRequestSuccess(request: ImageRequest, requestId: String, isPrefetch: Boolean) {
        super.onRequestSuccess(request, requestId, isPrefetch)
        println("request end")
    }
}