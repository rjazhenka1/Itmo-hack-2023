package ru.hackaton.profiler.exoplayer

import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.source.LoadEventInfo
import com.google.android.exoplayer2.source.MediaLoadData
import ru.hackaton.profiler.base.Library
import ru.hackaton.profiler.base.MeasurementService
import ru.hackaton.profiler.base.RequestType
import ru.hackaton.profiler.timelog.TimeLog

class ProfilerAnalyticsCollector(
    private val name: String
) : AnalyticsListener {
    private fun getRequestType(mediaLoadData: MediaLoadData): RequestType {
        if (mediaLoadData.trackType == C.TRACK_TYPE_VIDEO) {
            return RequestType.Video
        } else if (mediaLoadData.trackType == C.TRACK_TYPE_AUDIO) {
            return RequestType.Audio
        }
        return RequestType.Unknown;
    }


    override fun onLoadStarted(
        eventTime: AnalyticsListener.EventTime,
        loadEventInfo: LoadEventInfo,
        mediaLoadData: MediaLoadData
    ) {
        val measurement = MeasurementService.startMeasurement(
            loadEventInfo.loadTaskId.toString(),
            name,
            Library.ExoPlayer,
            getRequestType(mediaLoadData)
        )
    }

    override fun onLoadCompleted(
        eventTime: AnalyticsListener.EventTime,
        loadEventInfo: LoadEventInfo,
        mediaLoadData: MediaLoadData
    ) {
        val measurement = MeasurementService.endMeasurement(loadEventInfo.loadTaskId.toString())
        measurement?.size = loadEventInfo.bytesLoaded
        measurement?.url = loadEventInfo.uri.toString()
    }
}