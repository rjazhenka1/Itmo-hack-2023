package ru.ok.android.itmohack2023

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class Bebra {
    fun a() : String {
        return "no"
    }
}


//class Main {
//    var bebra = Bebra()
//}

fun main() {
    val bebra = Bebra()
    println(bebra.a())

    val properties = Bebra::class.memberFunctions

    val uuuuu  = properties.find {it.name == "a"} as KMutableProperty<*>?

    uuuuu?.isAccessible = true;

    uuuuu?.setter?.call(bebra, { -> "pepe" })

    println(bebra.a())
}