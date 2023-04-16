package ru.hackaton.profiler.fresco

import com.facebook.imagepipeline.listener.BaseRequestListener
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.request.ImageRequest
import ru.hackaton.profiler.base.Library
import ru.hackaton.profiler.base.MeasurementService
import ru.hackaton.profiler.base.RequestType

class ProfilerRequestListener(
    private val name: String
) : BaseRequestListener() {
    override fun onRequestStart(
        request: ImageRequest,
        callerContext: Any,
        requestId: String,
        isPrefetch: Boolean
    ) {
        MeasurementService.startMeasurement(requestId, name, Library.Fresco, RequestType.Image)
    }

    override fun onRequestFailure(
        request: ImageRequest,
        requestId: String,
        throwable: Throwable,
        isPrefetch: Boolean
    ) {
        val measurement = MeasurementService.endMeasurement(requestId)
        measurement?.status?.exception = Exception()
        measurement?.url = request.sourceUri.toString()
        measurement?.size =
            ((request.bytesRange?.from?.let { request.bytesRange?.to?.minus(it) }))?.toLong()
    }

    override fun onRequestSuccess(request: ImageRequest, requestId: String, isPrefetch: Boolean) {
        val measurement = MeasurementService.endMeasurement(requestId)
        measurement?.url = request.sourceUri.toString()
        measurement?.size =
            ((request.bytesRange?.from?.let { request.bytesRange?.to?.minus(it) }))?.toLong()
    }
}