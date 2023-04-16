package ru.hackaton.profiler.base

data class Status(
    val startTimestamp: Long = System.currentTimeMillis(),
    var endTimestamp: Long? = null,
    var exception: Exception? = null
)
