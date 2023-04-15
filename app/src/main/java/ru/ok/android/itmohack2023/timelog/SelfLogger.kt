package ru.ok.android.itmohack2023.timelog


import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


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
    init{
        if(!Files.exists(path)){
            Files.createFile(path)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun writeMess(name: String, timeStart:Any,  timeDuration: Any, mess: String) {
        val text = "$name,$timeStart,$timeDuration,$mess" + System.lineSeparator()
        try {
            Files.write(path, text.toByteArray(), StandardOpenOption.APPEND)
            println("Text appended to the file")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        print("sended")
    }
}

