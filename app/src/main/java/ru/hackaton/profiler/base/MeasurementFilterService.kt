package ru.hackaton.profiler.base

import ru.hackaton.profiler.base.filters.AllowAll
import ru.hackaton.profiler.base.filters.ExceptionFilter
import ru.hackaton.profiler.base.filters.LowSpeedFilter

object MeasurementFilterService {
    private val filters = arrayListOf(
        ExceptionFilter(),
        LowSpeedFilter(),
        AllowAll()
    )

    fun checkMeasurement(measurement: Measurement) {
        if (filters.any { it.doFilter(measurement) })
            MeasurementApi.sendToServer(measurement)
    }
}