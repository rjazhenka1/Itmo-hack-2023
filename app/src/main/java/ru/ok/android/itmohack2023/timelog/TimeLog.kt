package ru.ok.android.itmohack2023.timelog

import java.util.Calendar
import kotlin.system.measureTimeMillis

object TimeLog {
    private val times = ArrayList<Time>()

    fun <T> measure(code: () -> T): T {
        val calendar = Calendar.getInstance()
        val start = calendar.timeInMillis
        var result: T
        val time = measureTimeMillis { result = code() }
        times.add(Time(start, time))
        return result
    }


    data class Time(val callTime: Long, val timeInMillis: Long)
}