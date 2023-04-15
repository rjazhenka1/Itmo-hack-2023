package ru.ok.android.itmohack2023.wrapper

import javassist.ClassPool

class OkHttpWrapper {
    fun add() {
        val pool = ClassPool.getDefault()
        pool.appendClassPath("C:\\Users\\Phoenix\\.gradle\\caches\\modules-2\\files-2.1\\com.squareup.okhttp3\\okhttp\\4.10.0\\cd63657ac15770ed1420647154c9f44645533bef\\okhttp-4.10.0.jar")

        val claz = pool["okhttp3.internal.connection.RealCall"];

        val method = claz.getDeclaredMethod("execute")

        method.insertBefore("{System.out.println(\"Start\");}");
        method.insertAfter("{System.out.println(\"End\");}");

        claz.toClass()
    }
}