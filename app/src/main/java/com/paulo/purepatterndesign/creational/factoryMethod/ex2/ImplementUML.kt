package com.paulo.purepatterndesign.creational.factoryMethod.ex2


interface  Product{
    fun doStuff()
}

class ConcretProductA: Product {
    override fun doStuff() {
        TODO()
    }
}
class ConcretProductB: Product {
    override fun doStuff() {
        TODO()
    }
}

abstract class Creator {
    abstract fun someOperation():Product
    abstract fun createOperation():Product

    fun showDescription(){
        val p = createOperation()
        p.doStuff()
    }


}
class ConcreteCreatorA: Creator(){
    override fun someOperation(): Product {
        TODO()
    }

    override fun createOperation(): Product {
        TODO()
    }

}

fun main() {
    val product = ConcreteCreatorA()
    product.someOperation()
    product.showDescription()
    product.createOperation()

}