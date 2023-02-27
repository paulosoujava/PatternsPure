package com.paulo.justtips

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyUseCase {
    suspend operator fun invoke() = runCatching { delay(1000) }
}
class SolutionClass {
    suspend fun invoke() = com.paulo.justtips.resultOf {  delay(1000)   }
}

/*
O problema comsuspend runCatching
O exemplo abaixo mostra um caso de uso que simplesmente espera 1000 ms antes de retornar para
dentro runCatching. Ele o inicia, espera 500 ms e cancela o escopo — enquanto o caso
de uso está em execução. Você esperaria que todo o bloco de corrotina parasse de ser
executado, mas isso não acontece:
 */
fun main() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        //SolutionClass().invoke()
         MyUseCase().invoke()
            .onSuccess { println("Success") }
            .onFailure { println("Failure") }
        println("We're not stopping!")
    }

    Thread.sleep(500) // Cancel while executing use case
    scope.cancel()

// This will output the following:
// "Failure"
// "We're not stopping!"
}
