package ru.ok.android.itmohack2023.timelog


import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class MeasuringAspect {
    var url: String? = null;

    @Around("execution(* *(..)) && @annotation(ru.ok.android.itmohack2023.timelog.Measure)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        println(joinPoint.args)
        /*val url = joinPoint.args
            .filterIsInstance<String>()
            .firstOrNull { it::class.annotations.size > 0 }*/

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

/**
 * Annotation that marks a parameter with a URL for a function that is being logged
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class URL