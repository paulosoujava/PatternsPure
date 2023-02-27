package com.paulo.justtips

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


fun main() {
    val screenScope = CoroutineScope(Dispatchers.IO)

    println("Start")

    screenScope.launch {
        println("Launched")

        val result = runCatching {
            delay(1000)
            4
        }.getOrElse { 0 }

        writeToDatabase(result)
    }

    Thread.sleep(500)

    // User leaves the screen.
    screenScope.cancel()

    println("Job was cancelled: ${screenScope.isActive}")
    println("Done")
}

suspend fun writeToDatabase(result: Int) {
    println("Writing $result to database")
}