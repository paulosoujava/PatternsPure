package com.paulo.justtips

import androidx.annotation.CheckResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import java.util.concurrent.CancellationException

/*
Funciona basicamente da mesma forma,
é igualmente simples de usar, mas corrige
os problemas mencionados acima:

Apanhando Throwable: ✅ Corrigido!
Este pega Exceptionem vez disso,não engolindo erros de tempo de execução.

Atendendo as exceções de cancelamento: ✅ Corrigido!
Ele passa pelo CancellationException, fazendo com que as co-rotinas se
comportem conforme o esperado.
 */

//usage example
class PostHigthCommentUseCase {

    //no more runCatching replace to resultOf
    @CheckResult
    fun execute(com: Comment) = resultOf {
        validate(com)
        upload(com)
    }
}

/*
Why CheckResult
Embora eu tenha listado exceções sem
vazamento como uma vantagem, isso pode
resultar no início de um caso de uso e
no esquecimento de fazer algo com o valor
Result retornado. Por esse motivo, sempre
adiciono @CheckResultacima meus métodos de
invocação de caso de uso. Agora, o IDE fará questão
de me lembrar de fazer pelo menos alguma coisa com
o valor retornado do seu caso de uso. Observe que
isso está disponível apenas nas bibliotecas do AndroidX.
 */

//EXTENSIONS

inline fun <R> resultOf(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}

inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}
inline fun <R, T> Result<T>.mapResult(transform: (value: T) -> R): Result<R> {
    val successResult = getOrNull()
    return when {
        successResult != null -> resultOf { transform(successResult) }
        else -> Result.failure(exceptionOrNull() ?: error("Unreachable state"))
    }
}