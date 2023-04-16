package ru.hackaton.profiler.okhttp3

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.hackaton.profiler.base.Library
import ru.hackaton.profiler.base.MeasurementService
import ru.hackaton.profiler.base.RequestType
import ru.hackaton.profiler.base.Stacktrace
import ru.hackaton.profiler.timelog.TimeLog
import ru.hackaton.profiler.timelog.TimeLog.start
import java.io.IOException

class ProfilerInterceptor(
    private val name: String,
    private val library: Library
) : Interceptor {

    private fun getMethod(request: Request): RequestType {
        if (request.method == "GET") {
            return RequestType.Get
        } else if (request.method == "POST") {
            return RequestType.Post
        }
        return RequestType.Unknown
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val measurement = MeasurementService.startMeasurement(name, library, getMethod(request))
        measurement.url = request.url.toString()
        val response = chain.proceed(request)
        MeasurementService.endMeasurement(measurement.id)
        measurement.size = response.body?.contentLength()
        measurement.size = measurement.size?.plus(+response.headers.toString().length)
        measurement.size = measurement.size?.plus(+response.message.length)
        measurement.stackTrace = Stacktrace.getTraceback()
        return response
    }
}