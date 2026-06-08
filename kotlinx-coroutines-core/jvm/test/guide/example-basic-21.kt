package guide

import kotlinx.coroutines.*

//sampleStart
fun main() {
    // runBlocking uses the current thread for running all coroutines
    runBlocking {
        // val coroutineCount = 5
        // repeat(coroutineCount) { coroutineIndex ->
        //     launch {
        //         val id = coroutineIndex + 1
        //         repeat(5) { iterationIndex ->
        //             val iteration = iterationIndex + 1
        //             // Temporarily suspends to give other coroutines a chance to run
        //             // Without this, the coroutines run sequentially
        //             yield()
        //             // Prints the coroutine index and iteration index
        //             println("$id * $iteration = ${id * iteration}")
        //         }
        //     }
        // }
        val coroutineCount = 0
            launch {
                val id = coroutineCount + 1
                repeat(5) { iterationIndex ->
                    val iteration = iterationIndex + 1
                    // Temporarily suspends to give other coroutines a chance to run
                    // Without this, the coroutines run sequentially
                    yield()
                    // Prints the coroutine index and iteration index
                    println("$id * $iteration = ${id * iteration}")
                }
            }
    }

    println("All child jobs completed!")
}
