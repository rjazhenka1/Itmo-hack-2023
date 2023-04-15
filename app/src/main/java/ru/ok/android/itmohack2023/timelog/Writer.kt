package ru.ok.android.itmohack2023.timelog


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import java.io.IOException
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
class Writer {

    fun writeMess(data: DTO) {

        val client = OkHttpClient()

        val formBody = data.name?.let {
            data.url?.let { it1 ->
                FormBody.Builder()
                    .add("device_id", data.device_id.toString())
                    .add("id", data.id.toString())
                    .add("start_time", data.start_time.toString())
                    .add("duration", data.duration.toString())
                    .add("name", it)
                    .add("url", it1)
                    .add("data_size", data.data_size.toString())
                    .build()
            }
        }
        val request = formBody?.let {
            Request.Builder()
                .url("http://164.92.210.100:8080")
                .post(it)
                .build()
        }

        try {
            val response = request?.let { client.newCall(it).execute() }
            print(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

