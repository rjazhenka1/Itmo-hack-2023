package ru.hackaton.profiler.glide

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ProfilerGladRequestListener<R>: RequestListener<R> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<R>?,
        isFirstResource: Boolean
    ): Boolean {
        println("failed")
        return false
    }

    override fun onResourceReady(
        resource: R,
        model: Any?,
        target: Target<R>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        println("success")
        return false
    }
}