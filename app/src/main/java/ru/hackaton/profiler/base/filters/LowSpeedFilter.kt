package ru.hackaton.profiler.base.filters

import ru.hackaton.profiler.base.Measurement

class LowSpeedFilter : AbstractFilter() {
    // lower on 30%
    private val PERCENT = 0.3
    override fun doFilter(measurement: Measurement): Boolean {
        super.doFilter(measurement)
        if (this.lastMeasurements.size < 20 || measurement.size == null) {
            return false
        }

        var avarage = 0.0
        var count = 0

        for (i in this.lastMeasurements) {
            if (i.size != null && i.status.endTimestamp != null) {
                avarage += i.size!!.toFloat() / (i.stackTrace!!.size)
                count++
            }
        }
        val currentSpeed =
            measurement.size!!.toFloat() / (measurement.status.startTimestamp - measurement.status.endTimestamp!!)
        return avarage / count < currentSpeed * (1 + PERCENT)
    }
}