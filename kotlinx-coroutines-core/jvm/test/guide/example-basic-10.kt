package guide

import kotlinx.coroutines.*
//sampleStart
suspend fun main() {
    coroutineScope {
        launchAll()
    }
}

fun CoroutineScope.launchAll() { // this: CoroutineScope
    // Calls .launch() on CoroutineScope
    this.launch { println("1") }
    this.launch { println("2") }
}
//sampleEnd
/* -- Calling launch without declaring CoroutineScope as the receiver results in a compilation error --

fun launchAll() {
    // Compilation error: this is not defined
    this.launch { println("1") }
    this.launch { println("2") }
}
 */
