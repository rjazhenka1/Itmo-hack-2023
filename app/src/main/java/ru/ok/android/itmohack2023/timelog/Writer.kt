package ru.ok.android.itmohack2023.timelog


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.opencsv.CSVWriter
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Arrays
import java.util.Calendar


@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
class Writer {
    var logFileName = "stat.csv"

    @RequiresApi(Build.VERSION_CODES.O)
    var path = Paths.get(
        "app",
        "src",
        "main",
        "java",
        "ru",
        "ok",
        "android",
        "itmohack2023",
        "timelog",
        logFileName
    )
    private val CSV_HEADER = arrayOf<String>("nameFunction", "timeStart", "timeDuration", "url")
    private var csvWriter: CSVWriter?
    var formatter: SimpleDateFormat?

    init {
        if (!Files.exists(path)) {
            Files.createFile(path)
        }
        csvWriter = CSVWriter(FileWriter(path.toFile()))
        csvWriter!!.writeNext(CSV_HEADER)
        formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun writeMess(data: DTO) {
        try {
            val calendar = Calendar.getInstance()
            calendar.setTimeInMillis(data.timeStart)
            var durationInSeconds = data.timeDuration / 1000
            csvWriter!!.writeNext(
                listOf(
                    data.nameFunction,
                    formatter!!.format(data.timeStart),
                    durationInSeconds.toString() + "s " +
                            (data.timeDuration % 1000).toString() + "ms",
                    data.url
                ).toTypedArray()
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        print("sended")
    }

    fun close() {
        csvWriter!!.close()
    }
}

