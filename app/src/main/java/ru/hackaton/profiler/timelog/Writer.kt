package ru.hackaton.profiler.timelog


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
class Writer {

    fun writeMess(data: DTO) {
        val client = OkHttpClient()

        val formBody =
            FormBody.Builder()
                .add("id", data.id.toString())
                .add("start_time", data.start_time.toString())
                .add("duration", data.duration.toString())
                .add("name", data.name.toString())
                .add("url", data.url.toString())
                .add("data_size", data.data_size.toString())
                .build()


        val request = Request.Builder()
            .url("http://164.92.210.100:8080")
            .post(formBody!!)
            .build()

        try {
            println(123)
            println("request    $request")
            val response = client.newCall(request).execute()
            println("kek       $response")
        } catch (e: IOException) {
            println("Exception " + e.message)
            e.printStackTrace()
        }
    }
}

