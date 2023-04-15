package ru.ok.android.itmohack2023.wrapper

import okhttp3.OkHttpClient
import okhttp3.Request
import ru.ok.android.itmohack2023.timelog.TimeLog

// use --add-opens java.base/java.lang=ALL-UNNAMED
fun main() {
    OkHttpWrapper().add()
    // check connection
    val request: Request = Request.Builder()
        .url("https://cat-fact.herokuapp.com/facts")
        .build()


    TimeLog.measure ({
        OkHttpClient().newCall(request).execute()
            .use { response -> println(response.body?.string()) }
    }, "test", "https://cat-fact.herokuapp.com/facts")
}