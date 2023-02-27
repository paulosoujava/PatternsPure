package com.paulo.purepatterndesign

/*
Closures
Uma closure é a combinação de uma função
 com as referências ao estado que a circunda (o ambiente léxico).
 Em outras palavras, uma closure lhe dá acesso ao escopo de uma
 função externa a partir de uma função interna. Em JavaScript,
  as closures são criadas toda vez que uma função é criada,
  no momento da criação da função.

  RESUMINDO:
  Uma closure em Kotlin é uma função que captura
   e mantém acesso a variáveis locais do escopo
    externo, permitindo que essas variáveis sejam
     usadas pela função, mesmo após a conclusão do
      escopo externo. Isso torna as funções mais flexíveis e poderosas.

Escopo léxico
Considere a função abaixo
 */
fun init(){
    var name = "Mozilla" // name é uma variável local criada pelo init
    fun displayName(){
        // displayName() é a função interna, uma closure
        println(name) // usa a variável declarada na função pai
    }
    displayName()
}

/*
Closure
Nesse exemplo, a função saudacao retorna
uma função anônima que captura a variável
 mensagem do escopo externo. Quando a função
  anônima é executada através da variável cumprimento,
   ela pode acessar e imprimir o valor de mensagem,
    mesmo após a conclusão da chamada da função saudacao.
 */
fun saudacao(nome: String): () -> Unit {
    val mensagem = "Olá, $nome!"
    return {
        println(mensagem)
    }
}
fun String.saudacao(): () -> Unit {
    val mensagem = "Olá, $this!"
    return {
        println(mensagem)
    }
}


fun main() {


    val cumprimento = saudacao("João")

    cumprimento() // imprime "Olá, João!"
}

