package guide

// Imports the coroutines library
import kotlinx.coroutines.*

// Imports the kotlin.time.Duration to express duration in seconds
import kotlin.time.Duration.Companion.seconds

// Defines a suspending function
suspend fun greet() {
    println("The greet() on the thread: ${Thread.currentThread().name}")
    // Suspends for 1 second and releases the thread
    delay(1.seconds)
    // The delay() function simulates a suspending API call here
    // You can add suspending API calls here like a network request
}

suspend fun main() {
    // Runs the code inside this block on a shared thread pool
    withContext(Dispatchers.Default) { // this: CoroutineScope
        this.launch() {
            greet()
        }

        // Starts another coroutine
        this.launch() {
            println("The CoroutineScope.launch() on the thread: ${Thread.currentThread().name}")
            delay(1.seconds)
            // The delay function simulates a suspending API call here
            // You can add suspending API calls here like a network request
        }

        println("The withContext() on the thread: ${Thread.currentThread().name}")
    }
}
