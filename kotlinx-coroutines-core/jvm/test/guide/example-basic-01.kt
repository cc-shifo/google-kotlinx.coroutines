// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic01

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

// 在文件上右键，选择运行/debug；或者直接在工具栏上点击运行或调试按钮
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
