package ru.ok.android.itmohack2023.timelog

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Calendar
import kotlin.random.Random

data class DTO(
    val device_id: Long?,
    val id: Long?,
    val start_time: Long?,
    var duration: Long?,
    val name: String?,
    val url: String?,
    var data_size: Long?
)

object TimeLog {
    private val values = HashMap<Long, DTO>()
    private val pending = HashMap<Long, Long>()

    @RequiresApi(Build.VERSION_CODES.O)
    private var writer = Writer();

    fun start(
        device_id: Long?,
        start_time: Long?,
        name: String?,
        url: String?,
    ): Long {
        var id: Long;
        do {
            id = Random.nextLong()
        } while (pending.containsKey(id))

        pending[id] = Calendar.getInstance().timeInMillis
        values[id] = DTO(
            device_id = device_id,
            id = id,
            duration = null,
            start_time = start_time,
            name = name,
            url = url,
            data_size = null
        );

        return id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun end(id: Long, data_size: Long?) {
        val end = Calendar.getInstance().timeInMillis
        val start: Long = pending[id]!!
        pending.remove(id);

        values[id]!!.duration = end - start
        values[id]!!.data_size = data_size

        // sender stat to csv.file
        values[id]?.let { writer.writeMess(it) }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun <T> measure(code: () -> T, name: String, url: () -> String?): T {
        val calendar = Calendar.getInstance()
//        val id = start(name, url)
        var result = code()
//        end(id)

        return result
    }

    data class Time(val callTime: Long, val timeInMillis: Long)
}