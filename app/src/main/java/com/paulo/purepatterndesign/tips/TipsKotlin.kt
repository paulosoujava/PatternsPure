package com.paulo.purepatterndesign

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.toLowerCase
import java.nio.file.Files
import java.util.*
import kotlin.collections.HashMap

data class Employee(
    var name: String = "",
    var age: Int = 18

)

/*
clausura é uma função que referencia variáveis livres no contexto
 */

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val emp = Employee()
    emp.age = 20
    emp.name = "Paulo"

    //retorna um Employee, devolve a instancia original
    //principal uso é deixar o codigo que precisa inicializar uma instancia mais legivel
    //
    val employee = emp.apply {
        age = 21
        name = "Paulo"
    }

    //nao retorna nada Unit devolve o valor da propria closure, quando não huver necessidade de manter uma referencia para o obj original
    val result = emp.let {
        it.name = "Paulo"
        it.age = 21
        "Name: ${it.name} age =${it.age}"
    }
    // a vantagem é que nao precisamos atribuir o path original a uma variavel intermediaria
    val outPaht = kotlin.io.path.Path("/user/home").let {
        val path = it.resolve("output")
        path.toFile().createNewFile()
        path
    }


//nao retorna nada Unit, quando desejarmos chamar varias funcoes em um objeto, mas nao queremos repetir
    // o receptor da chamada
    val y = with(emp) {
        name = "Paulo"
        age = 23
    }

    //nao retorna nada Unit
    /*
        combina o casso de uso eith e let isso significa que uma closure é passada para o run
        o valor de retorno da closure é usado como valor de retorno do proprio run
        a principal diferença entre let e run é que com  run o receptor
        é a instanciam enquanto em let o argumento da closure é a instancia
     */
    val x = emp.run {
        name = "Paulo"
        age = 24
    }
    /*
    encapsula uma chamada de funcao custosa de modo
    que sera chamada quando for necessario pela primeira vez
     */
    val empLazy = lazy {
        readFromDBVeryVeryCustosa()
    }

    /*
    semelhante ao try catch com recursos de try-with-resources existente no java 7
    use chama uma funcao de forma segura fechando o recurso apos a funcao ter concluido independentemente de uma excecao ter sido ou nao lancada
     */
    val input = Files.newInputStream(kotlin.io.path.Path("input.txt"))
    val byte = input.use { input.read() }

    /*
    triade required assert check
    required: lanca uma excecao, usada para garantir que os argumentos correspondam as condicoes de entrada
    assert laca uma AssertionException, usada para grantir que nosso estado interno seja consistente
    check: laca uma IllegalStateException, usada para TAMBEM para consistencia de estado interno
     */
    fun testTriadeRequire(str: String){
        require(str.isEmpty()) { " string  nao deve ser vazia"}
        print(str)
    }
    fun testTriadeAssert(str: String){
        assert(str.isEmpty()) { " string  nao deve ser vazia"}
        print(str)
    }
    fun testTriadeCheck(str: String){
        check(str.isEmpty()) { " string  nao deve ser vazia"}
        print(str)
    }

    //referencia a fnc
    fun isEven(n: Int): Boolean = n % 2 == 0
    var ints = listOf(1, 2, 3)
    ints.filter(::isEven)
    //referencia vinculada
    fun String.equalsIgnoreCase(str: String )= this.lowercase(Locale.ROOT) == str.lowercase(Locale.ROOT)
    listOf("A", "B", "C", "D", "E", "F").filter {
        (String::equalsIgnoreCase)("bar", it)
    }
    //alias
    fun process(param: bigParams){}



}
typealias bigParams = HashMap<Boolean, HashMap<List<Int>, List<String>>>
fun readFromDBVeryVeryCustosa(){}