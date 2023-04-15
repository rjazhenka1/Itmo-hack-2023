package ru.ok.android.itmohack2023.timelog

import java.util.Calendar
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class DTO(val nameFunction: String, val timeStart: Long, var timeDuration:Long, var url:String)

object TimeLog {
    private val times = ArrayList<Time>()
    private val pending = HashMap<Int, Long>()

    fun start(): Int {
        var id: Int;
        do {
            id = Random.nextInt()
        } while (pending.containsKey(id))

        pending[id] = Calendar.getInstance().timeInMillis
        return id
    }

    fun end(id: Int) {
        val end = Calendar.getInstance().timeInMillis
        val start : Long = pending[id]!!
        pending.remove(id);

        times.add(Time(start, end - start))
    }


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