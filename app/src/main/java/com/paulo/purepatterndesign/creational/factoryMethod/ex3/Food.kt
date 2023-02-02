package com.paulo.purepatterndesign.creational.factoryMethod.ex3

// Durante muito tempo o ZeFood só fazia XTudo
// porem com o crescimento gostaria de amadurecer o software
// e adivinha vc é o cara.
class XTudo(
    val queijo: Boolean,
    val alface: Boolean,
    val batataPalha: Boolean,
)

interface Food {
    fun makeFood()
}

class Xegg(
    val queijo: Boolean,
    val alface: Boolean
) : Food {
    override fun makeFood() {
        println("Criando XEGG com alface $alface e queijo $queijo")
    }
}

class Xdog(
   val carne: Boolean,
    val alface: Boolean
) : Food {
    override fun makeFood() {
        println("Criando XDOG com carne $carne e alface $alface")
    }
}

abstract class XFood {
    abstract fun createFood(type: String): Food
}

class MakeAxDog : XFood() {
    override fun createFood(type: String): Food {
        val food = when (type) {
            "simples" -> Xdog(carne = true, alface = false)
            "completo" -> Xdog(carne = true, alface = true)
            "vegano" -> Xdog(carne = false, alface = true)
            else -> throw IllegalAccessException("Boom")
        }
        food.makeFood()
        return food
    }
}

class MakeAxEgg : XFood() {
    override fun createFood(type: String): Food {
        val food = when (type) {
            "simples" -> Xegg(queijo = true, alface = false)
            "completo" -> Xegg(queijo = true, alface = true)
            "vegano" -> Xegg(queijo = false, alface = true)
            else -> throw IllegalAccessException("Boom")
        }
        food.makeFood()
        return food
    }
}

fun main() {
    val list = listOf(MakeAxEgg(), MakeAxDog())
    list.forEach {
        it.createFood("simples")
        it.createFood("completo")
        it.createFood("vegano")
    }


}