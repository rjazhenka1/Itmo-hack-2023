package ru.hackaton.profiler.base

import kotlin.random.Random

object MeasurementService {
    private val base = HashMap<String, Measurement>()

    private fun generateId(): String {
        var id: String;
        do {
            id = Random.nextLong().toString()
        } while (base.containsKey(id))
        return id;
    }

    fun startMeasurement(name: String, library: Library): Measurement {
        return startMeasurement(generateId(), name, library, RequestType.Unknown);
    }

    fun startMeasurement(name: String, library: Library, type: RequestType): Measurement {
        return startMeasurement(generateId(), name, library, type);
    }

    fun startMeasurement(
        id: String,
        name: String,
        library: Library,
        type: RequestType
    ): Measurement {
        val measurement = Measurement(id, name, library, type)
        base[id] = measurement
        return measurement
    }

    fun endMeasurement(id: String): Measurement? {
        val measurement = base[id];
        measurement?.status?.endTimestamp = System.currentTimeMillis()
        if (measurement != null) {
            MeasurementFilterService.checkMeasurement(measurement)
        }
        return measurement
    }
}