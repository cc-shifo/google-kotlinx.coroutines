package guide

// Imports the kotlin.time.Duration to enable expressing duration in milliseconds
import kotlin.time.Duration.Companion.milliseconds

import kotlinx.coroutines.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

//sampleStart
suspend fun main() = withContext(Dispatchers.Default) { // this: CoroutineScope
    // Starts downloading the first page
    val firstPage = this.async {
        delay(500.milliseconds)
        println("firstPage async in background on the thread: ${Thread.currentThread().name}")
        "First page"
        // "Second page"
    }

    // Starts downloading the second page in parallel
    val secondPage = this.async {
        // delay(100.milliseconds)
        delay(100.toDuration(DurationUnit.MILLISECONDS))
        println("secondPage async in background on the thread: ${Thread.currentThread().name}")
        "Second page"
    }

    // Awaits both results and compares them
    val pagesAreEqual = firstPage.await() == secondPage.await()
    println("Pages are equal: $pagesAreEqual on the thread: ${Thread.currentThread().name}")
}
