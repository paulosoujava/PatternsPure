package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.base


import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.TypeCoffee

abstract class CoffeeShop(generateTable: Int) {
     var listOfTables: MutableMap<Int, Boolean> = mutableMapOf()


    init {
        repeat(generateTable) {
            listOfTables[it] = false
        }


    }

    abstract fun orderCoffee(type: TypeCoffee): String
    abstract fun cleanTables(number: Int)
    abstract fun setOnTable(number: Int)

    fun closeShop() = false
    fun openShop() = true

}