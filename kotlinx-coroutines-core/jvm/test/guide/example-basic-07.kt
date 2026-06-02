package guide

import kotlinx.coroutines.*

/**
 * 可以学习到匿名函数简写的Lambda的转换过程
 */
fun main() = runBlocking {
    val deferreds: List<Deferred<Int>> = (1..3).map {
        async {
            delay(1000L * it)
            println("Loading $it")
            it
        }
    }

    // map参数形式：完整匿名函数
    val deferreds1: List<Deferred<Int>> = (1..3).map(fun(index : Int) : Deferred<Int> {
        return async {
            delay(1000L * index)
            println("Loading $index")
            index
        }
    })

    // map参数形式：完整匿名函数
    val deferreds2: List<Deferred<Int>> = (1..3).map(
        transform = fun(index: Int): Deferred<Int> {
            return async {
                delay(1000L * index)
                println("Loading $index")
                index
            }
        }
    )

    // map参数形式：完整匿名函数转Lambda，转换规则是，{参数列表 -> 返回值}
    val deferreds3: List<Deferred<Int>> = (1..3).map( { index : Int ->

        async {
            delay(1000L * index)
            println("Loading $index")
            index
        }
    })
    val sum = deferreds.awaitAll().sum()
    println("$sum")

    // 步骤 1.1: 移除 fun 关键字和省略返回类型（因为可被推理出来）
    // val deferreds1_1: List<Deferred<Int>> = (1..3).map(
    //     (index: Int) {  // 移除了 fun 和 : Deferred<Int>
    //     return async {
    //         delay(1000L * index)
    //         println("Loading $index")
    //         index
    //     }
    // }
    // )
    // 步骤 1.2: 移除参数列表，在{ }内行首添加 参数列表 ->
    // val deferreds1_2: List<Deferred<Int>> = (1..3).map(
    //     { index: Int ->  // 圆括号变花括号，添加 ->
    //         return async {
    //             delay(1000L * index)
    //             println("Loading $index")
    //             index
    //         }
    //     }
    // )
    // 步骤 2: 移除 return 关键字
    // val deferreds2_1: List<Deferred<Int>> = (1..3).map(
    //     { index: Int ->
    //         async {  // 移除 return，async 的结果自动成为返回值
    //             delay(1000L * index)
    //             println("Loading $index")
    //             index
    //         }
    //     }
    // )
    // 步骤 3: 尾部Lambda处理。将 Lambda 移到函数括号外（尾随 Lambda）
    // val deferreds3_1: List<Deferred<Int>> = (1..3).map() { index: Int ->
    //     async {
    //         delay(1000L * index)
    //         println("Loading $index")
    //         index
    //     }
    // }
    // 步骤 4: 省略map函数的空括号（移除参数后是空的）
    // val deferreds4_1: List<Deferred<Int>> = (1..3).map { index: Int ->
    //     async {
    //         delay(1000L * index)
    //         println("Loading $index")
    //         index
    //     }
    // }
    // 步骤 5: 省略Lambda内参数列表之中的类型（类型推断）
    // val deferreds5_1: List<Deferred<Int>> = (1..3).map { index ->
    //     async {
    //         delay(1000L * index)
    //         println("Loading $index")
    //         index
    //     }
    // }
    // 步骤 6: Lambda单参数情况，可省略参数列表，-> 符号，直接使用 it（可选）代表引用
    // val deferreds6_1: List<Deferred<Int>> = (1..3).map {
    //     async {
    //         delay(1000L * it)
    //         println("Loading $it")
    //         it
    //     }
    // }
}
