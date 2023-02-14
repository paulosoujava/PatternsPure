package com.paulo.scriptclass.designPattern.ducks
/*
O Strategy é um padrão de projeto
comportamental que permite que
você defina uma família de
algoritmos, coloque-os em classes
separadas, e faça os objetos deles intercambiáveis.
 */


interface FlyBehavior{
    fun fly()
}
interface QuackyBehavior{
    fun quack()
}

abstract class Ducky{
    //comportamentos que variam
    lateinit var flyBehavior: FlyBehavior
    lateinit var quackyBehavior: QuackyBehavior

    abstract fun display()

    fun performFly(){
        flyBehavior.fly()
    }
    fun performQuacky(){
        quackyBehavior.quack()
    }
}

class FlyWithWings: FlyBehavior{
    override fun fly() {
        TODO("Not yet implemented")
    }
}
class FLyNoWay: FlyBehavior{
    override fun fly() {
        TODO("Not yet implemented")
    }
}
class FLyRocketPowered: FlyBehavior{
    override fun fly() {
        TODO("Not yet implemented")
    }
}
class Quack: QuackyBehavior{
    override fun quack() {
        TODO("Not yet implemented")
    }
}
class MuteQuack: QuackyBehavior{
    override fun quack() {
        TODO("Not yet implemented")
    }
}
class Squeak: QuackyBehavior{
    override fun quack() {
        TODO("Not yet implemented")
    }
}

class MallarDuck:Ducky(){
    init {
        quackyBehavior = Quack()
        flyBehavior = FlyWithWings()
    }
    override fun display() {
        TODO("Not yet implemented")
    }
}
class ModelDuck:Ducky(){
    init {
        quackyBehavior = Quack()
        flyBehavior = FlyWithWings()
    }
    override fun display() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val mallard = MallarDuck()
    mallard.performFly()
    mallard.performQuacky()

}