package ru.ok.android.itmohack2023.timelog

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.datatransport.runtime.dagger.Component
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import kotlin.coroutines.Continuation

@Aspect
class MeasuringAspect {

    //@Around("within(ru.ok.android.itmohack2023.*)")
    @RequiresApi(Build.VERSION_CODES.O)
    @Around("@annotation(ru.ok.android.itmohack2023.timelog.Measure)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        val id = TimeLog.start()
        val result: Any? = joinPoint.proceed(joinPoint.args)
        println("SDOH $result");
        TimeLog.end(id)
        return result
    }
}

annotation class Measure