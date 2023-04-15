package ru.ok.android.itmohack2023.timelog

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.internal.wait
import java.util.Calendar
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class DTO(val nameFunction: String, val timeStart: Long, var timeDuration:Long?, var url:String?)

object TimeLog {
    private val values = HashMap<Long, DTO>()
    private val pending = HashMap<Long, Long>()

    fun start(name:String, url:String?=null): Long {
        println("START")
        var id: Long;
        do {
            id = Random.nextLong()
        } while (pending.containsKey(id))

        pending[id] = Calendar.getInstance().timeInMillis
        values[id] = DTO(name, id, -1, url);

        return id
    }

    fun end(id: Long) {
        val end = Calendar.getInstance().timeInMillis
        val start : Long = pending[id]!!
        pending.remove(id);

        println("SDOH" + values[id]!!.url)

        values[id]!!.timeDuration = end - start

    }


    fun <T> measure(code: () -> T, name:String, url:String?): T {
        val calendar = Calendar.getInstance()
        val id = start(name, url)
        var result = code()
        end(id)

        return result
    }

    data class Time(val callTime: Long, val timeInMillis: Long)
}