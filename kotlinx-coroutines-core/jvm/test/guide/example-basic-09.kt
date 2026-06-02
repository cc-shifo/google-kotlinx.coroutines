package guide

import kotlin.time.Duration.Companion.seconds

import kotlinx.coroutines.*

// If the coroutine context doesn't specify a dispatcher,
// CoroutineScope.launch() uses Dispatchers.Default
//sampleStart
suspend fun main() {
    // Root of the coroutine subtree
    coroutineScope { // this: CoroutineScope
        this.launch {
            this.launch {
                delay(2.seconds)
                println("Child of the enclosing coroutine completed on the thread: ${Thread.currentThread().name}")
            }
            println("Child coroutine 1 completed on the thread: ${Thread.currentThread().name}")
        }
        this.launch {
            delay(1.seconds)
            println("Child coroutine 2 completed on the thread: ${Thread.currentThread().name}")
        }
    }
    // Runs only after all children in the coroutineScope have completed
    println("Coroutine scope completed on the thread: ${Thread.currentThread().name}")
}
