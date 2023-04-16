package ru.ok.android.itmohack2023.timelog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContentProviderCompat.requireContext
import java.security.AccessController.getContext
import java.util.Calendar
import kotlin.random.Random

data class DTO(
    val id: Long?,
    val start_time: Long?,
    var duration: Long?,
    val name: String?,
    val url: String?,
    var data_size: Long?,
    var error: Boolean = true
)

object TimeLog {
    private val values = HashMap<Long, DTO>()
    private val pending = HashMap<Long, Long>()

    fun start(
        name: String?,
        url: String?,
    ): Long {
        val start_time = System.currentTimeMillis()

        var id: Long;
        do {
            id = Random.nextLong()
        } while (pending.containsKey(id))

        pending[id] = start_time
        values[id] = DTO(
            id = id,
            duration = null,
            start_time = start_time,
            name = name,
            url = url,
            data_size = null
        );

        return id
    }


    fun end(id: Long, error: Boolean = false) {
        val end = Calendar.getInstance().timeInMillis
        val start: Long = pending[id]!!
        pending.remove(id);

        values[id]!!.duration = end - start
        values[id]!!.error = error
        var writer = Writer()
        values[id]?.let { writer.writeMess(it) }
        println("OK" + values[id]!!.url + " " + values[id]!!.name)
    }


    fun <T> measure(code: () -> T, name: String, url: String?): T {
        val id = start(name, url)
        val result = code()
        end(id)
        return result
    }

    data class Time(val callTime: Long, val timeInMillis: Long)
}