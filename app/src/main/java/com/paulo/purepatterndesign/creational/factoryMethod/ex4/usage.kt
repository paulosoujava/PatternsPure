package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory

import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.TypeCoffee
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.factory.floripa.FloripaCoffeeShop
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.factory.sp.SaoPauloCoffeeShop

fun main() {
    val floripa = FloripaCoffeeShop(5)
    println("Coffee shop is: OPEN [${floripa.openShop()}]")
    floripa.listOfTables.forEach { println("${it.key+1} - ${it.value}") }
    println()
    println()

    floripa.setOnTable(1)
    floripa.listOfTables.forEach { println("${it.key+1} - ${it.value}") }
    println(floripa.orderCoffee(TypeCoffee.CAPPUCCINO))



    val sp = SaoPauloCoffeeShop(5)
    println("Coffee shop is: OPEN [${sp.openShop()}]")
    sp.listOfTables.forEach { println("${it.key+1} - ${it.value}") }
    println()
    println()

    sp.setOnTable(1)
    sp.listOfTables.forEach { println("${it.key+1} - ${it.value}") }
    println(sp.createASpecial())

}