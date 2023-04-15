package ru.ok.android.itmohack2023.timelog


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.datatransport.runtime.dagger.Component
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class MeasuringAspect {
    var url: String? = null;

    @Around("execution(* *(..)) && @annotation(ru.ok.android.itmohack2023.timelog.Measure)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        println(joinPoint.args)
        val url = joinPoint.args
            .filterIsInstance<String>()
            .firstOrNull { it::class.java.isAnnotationPresent(URL::class.java) }

        val id = TimeLog.start(joinPoint.toShortString(), url)
        val result: Any? = joinPoint.proceed()
        TimeLog.end(id)
        return result
    }
}

/**
 * An annotation that enables logging for a method
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Measure

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class URL