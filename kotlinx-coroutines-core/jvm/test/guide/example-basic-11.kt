package guide

import kotlin.time.Duration.Companion.milliseconds

import kotlinx.coroutines.*

suspend fun main() {
    withContext(Dispatchers.Default) {
        println("1.Child coroutine 1 completed on the thread: ${Thread.currentThread().name}")
        performBackgroundWork()
    }
    println("5.withContext scope completed on the thread: ${Thread.currentThread().name}")
}

//sampleStart
suspend fun performBackgroundWork() = coroutineScope { // this: CoroutineScope
    println("2.Scope start on the thread: ${Thread.currentThread().name}")
    // Starts a coroutine that runs without blocking the scope
    this.launch {
        // Suspends to simulate background work
        delay(100.milliseconds)
        println("4.Sending notification in background on the thread: ${Thread.currentThread().name}")
    }

    // Main coroutine continues while a previous one suspends
    println("3.Scope continues on the thread: ${Thread.currentThread().name}")
}
