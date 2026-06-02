package guide


// Imports the kotlin.time.Duration to enable expressing duration in milliseconds
import kotlin.time.Duration.Companion.milliseconds

import kotlinx.coroutines.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

//sampleStart
suspend fun main() { // this: CoroutineScope
    println("main start on the thread: ${Thread.currentThread().name}")
    MyRepository.readItem()
    println("main completed on the thread: ${Thread.currentThread().name}")
}

// A third-party interface you can't change
interface Repository {
    fun readItem(): Int
}

object MyRepository : Repository {
    override fun readItem(): Int {
        // Bridges to a suspending function
        return runBlocking {
            myReadItem()
        }
    }
}

suspend fun myReadItem(): Int {
    println("myReadItem start delay on the thread: ${Thread.currentThread().name}")
    delay(100.milliseconds)
    return 4
}
