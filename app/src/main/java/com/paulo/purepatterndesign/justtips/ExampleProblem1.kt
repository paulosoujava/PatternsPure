package com.paulo.justtips

data class Comment( val text: String)

fun validate(comment: Comment){}
fun upload(comment: Comment){}

class PostCommentUseCase() {
    fun execute(comment: Comment) = runCatching {
        // Se qualquer um desses códigos lançar uma exceção,

        // este bloco o envolve em um Result.
        validate (comment)

        // Caso contrário, o valor de retorno de upload() será agrupado em um Result.
        upload(comment)
    }
}

//usage
fun main() {
    PostCommentUseCase()
        .execute(Comment("blah"))
        .onFailure {  }
        .onSuccess {  }
}
/*
VER TIP@
Desvantagens
Embora o uso de Result com este método runCatching seja muito simples, existem algumas falhas:

runCatchingpega Throwable. Isso também significa que ele captura Errors como OutOfMemoryError
. Consulte StackOverflow – Diferença entre usar Throwable e Exception em um try catch para saber
 por que isso nem sempre deve ser feito. Compartilho uma solução proposta para isso mais abaixo.
runCatchingnão relança exceções de cancelamento. Isso o torna uma má escolha para corrotinas, pois
 quebra a simultaneidade estruturada . Leia mais sobre isso, incluindo uma solução proposta mais
  abaixo.
Não é possível especificar um Exceptiontipo sem erro ou uma classe de exceção base personalizada.
 Alternativas como kotlin-result oferecem essa funcionalidade.
 */