package com.paulo.purepatterndesign

import android.util.Log
import java.util.logging.Logger
import kotlin.properties.Delegates

interface IMoving {
    fun move()
}

class Walking : IMoving {
    override fun move() {
        println("Walking")
    }
}

interface IEating {
    fun eat()
}

class MeatEater : IEating {
    override fun eat() {
        println("Eat meat")
    }

}

class Animal(private val movable: IMoving) : IMoving {

    override fun move() {
        movable.move()
    }
}

class AnimalWithDelegation(movable: IMoving, eating: IEating) : IMoving by movable,
    IEating by eating {

    //se eu quiser posso sobrescrever
    override fun move() {
        println("Walking very fast")
    }
}

/*
Delegar de uma propriedade para outra pode ser um truque
 bastante útil para fornecer compatibilidade com versões
 anteriores. Imagine na versão 1.0 de uma classe que você
 tenha algumas funções de membro públicas. Mas durante sua vida
 o nome mudou. Para não interromper os clientes, podemos marcar
 as antigas funções de membro como obsoletas (via anotação) e
 encaminhar suas chamadas para a nova implementação.
 */
class Example {
    var newName: String = ""

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: String by this::newName
}

class Book {
    var content: String by Delegates.observable("") { property, oldValue, newValue ->
        println("Book changed")
    }
}

/*
By vetoable
O vetável é semelhante ao delegado observador.
 A função callback, no entanto, pode ser usada para desfazer a
 alteração. Observe que o retorno de chamada deve retornar
  um valor booleano. Em caso de sucesso, é verdadeiro e em
   caso de fracasso (veto), é falso . Em nosso exemplo
   de livro, verificamos se o novo conteúdo do livro é
    nulo ou vazio. Neste caso, é considerado errado e queremos vetar a alteração.
 */
class BookVetoable {
    var content: String by Delegates.vetoable("") { property, oldValue, newValue ->
        !newValue.isNullOrEmpty()
    }
}

/*
Um caso de uso comum é para variáveis de membro
que levam muito tempo para inicializar, mas não
serão usadas diretamente no início. Por exemplo,
considere uma classe Book que obtém uma string,
representando todo o texto. A classe do livro
contém outra classe que está fazendo pós-processamento
caro no livro. Os desenvolvedores decidiram tornar
 essa instância preguiçosa, pois nem sempre é chamada.
 */
class BookLazy(private val rawText: String) {

    private val analyser: Analyser by lazy { Analyser() }

    fun analyse() {
        analyser.doSomething()
    }
}

class Analyser {
    init {
        println("Init Analyser class")
    }

    fun doSomething() {
        println("DoSomething")
    }
}

fun main() {
    /* val movable = Walking()
     val animal = Animal(movable)
     animal.move()

     val movableWithDelegation = Walking()
     val eating = MeatEater()
     val animalWithDelegation = AnimalWithDelegation(movableWithDelegation, eating)
     animalWithDelegation.move()

     val example = Example()
     example.oldName = "hello"

     println(example.newName)*/

    val book = Book()
    book.content = "New content"

    val bookV = BookVetoable()
    bookV.content = "New content"
    println(bookV.content)

    bookV.content = ""
    println(bookV.content)


    val rawText = "MyBook"
    val bookL = BookLazy(rawText)
    println("Book is created")
    bookL.analyse()
}
