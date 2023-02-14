package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.factory.floripa

import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.base.CoffeeShop
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.Cappiccino
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.Espresso
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.House
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.TypeCoffee


class FloripaCoffeeShop(quantityTable: Int) : CoffeeShop(generateTable = quantityTable) {
    private val extra: String = "Floripa Coffee Shop"

    override fun orderCoffee(type: TypeCoffee): String {
        return when (type) {
            TypeCoffee.CAPPUCCINO -> {
                val c = Cappiccino()
                c.make(extra)
            }
            TypeCoffee.ESPRESSO -> {
                val e = Espresso()
                e.make(extra)
            }
            TypeCoffee.HOUSE -> {
                val h = House()
                h.make(extra)
            }
        }
    }

    override fun cleanTables(number: Int) {
        listOfTables.remove(number)
    }

    override fun setOnTable(number: Int) {
        listOfTables[number] = true
    }

}