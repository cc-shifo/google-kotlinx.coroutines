// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic06

import kotlinx.coroutines.*

fun main() = runBlocking {
   repeat(5) { // launch a lot of coroutines 50_000 is too much.
       launch {
           delay(5000L)
           print(".")
       }
   }

    coroutineContext[Job]?.children?.forEach { it.join() }
    println("abcd1111111")

    val ctx1 = CoroutineName("A") + Job()
    val ctx2 = CoroutineName("B") + Dispatchers.Default
    // 执行 ctx1 + ctx2
    val result = ctx1 + ctx2
    // 内部等价于：
    ctx2.fold(ctx1) { acc, element ->
        // 迭代1: element = CoroutineName("B")
        //   - 从 acc 中移除 key 为 CoroutineName 的元素（即 CoroutineName("A")）
        //   - 添加 CoroutineName("B")
        //   - 返回新的上下文

        // 迭代2: element = Dispatchers.Default
        //   - 从 acc 中移除 key 为 ContinuationInterceptor 的元素（如果有）
        //   - 添加 Dispatchers.Default
        //   - 确保拦截器在最后（性能优化）
        //   - 返回最终的上下文
        ctx1
    }

    return@runBlocking

}

//fun myWithLock(lock: Any, action: () -> Unit) {
//    println("获取锁")
//    action()  // 编译器不知道 action 会不会执行
//    println("释放锁")
//}
//
//fun example1() {
//    val value: Int  // 声明但不初始化
//
//    myWithLock(lock) {
//        value = 42  // 在这里赋值
//    }
//
//    println(value)  // ❌ 编译错误：Variable 'value' must be initialized
//}
