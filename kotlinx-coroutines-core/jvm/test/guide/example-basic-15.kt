package guide

import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

suspend fun main() {
    withContext(Dispatchers.Default) {
        println("=== External coroutine context ===")
        printCoroutineContext("External", coroutineContext)
        // Launches 50,000 coroutines that each wait five seconds, then print a period
        printPeriods()
    }
}

//sampleStart
suspend fun printPeriods() = coroutineScope { // this: CoroutineScope
    println("=== Internal coroutineScope context ===")
    printCoroutineContext("Internal", coroutineContext)
    // Launches 50,000 coroutines that each wait five seconds, then print a period
    repeat(50_000) { index ->
        this.launch {
            if (index < 5) { // 只打印前几个协程的信息以避免输出过多
                println("Child coroutine $index context:")
                printCoroutineContext("Child $index", coroutineContext)
            }
            delay(5.seconds)
            print(".")
        }
    }
}

private fun printCoroutineContext(label: String, context: CoroutineContext) {
    println("  [$label] Dispatcher: ${context[ContinuationInterceptor]?.let { it::class.simpleName }}")
    println("  [$label] Job: ${context[Job]}")
    println("  [$label] Job parent: ${context[Job]?.parent}")
    println("  [$label] Coroutine name: ${context[CoroutineName]}")
    println("  [$label] Thread: ${Thread.currentThread().name}")
    println("  [$label] Full context: $context")
    println()
}
