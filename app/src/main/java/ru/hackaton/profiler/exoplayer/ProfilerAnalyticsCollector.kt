package ru.hackaton.profiler.exoplayer

import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.analytics.DefaultAnalyticsCollector
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.util.Clock

class ProfilerAnalyticsCollector : AnalyticsListener {
    override fun onAudioEnabled(
        eventTime: AnalyticsListener.EventTime,
        decoderCounters: DecoderCounters
    ) {
        super.onAudioEnabled(eventTime, decoderCounters)
        println("enable")
    }

    override fun onAudioDisabled(
        eventTime: AnalyticsListener.EventTime,
        decoderCounters: DecoderCounters
    ) {
        super.onAudioDisabled(eventTime, decoderCounters)
        println("disable")
    }

    override fun onVideoEnabled(
        eventTime: AnalyticsListener.EventTime,
        decoderCounters: DecoderCounters
    ) {
        super.onVideoEnabled(eventTime, decoderCounters)
        println("enable")
    }

    override fun onVideoDisabled(
        eventTime: AnalyticsListener.EventTime,
        decoderCounters: DecoderCounters
    ) {
        super.onVideoDisabled(eventTime, decoderCounters)
        println("disable")
    }
}