package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.factory.sp

import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.base.CoffeeShop
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.Cappiccino
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.Espresso
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.House
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees.TypeCoffee
import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.contracts.SpecialCoffee

class SaoPauloCoffeeShop(quantityTable: Int) :  CoffeeShop(generateTable = quantityTable),
    SpecialCoffee {
    private val extra: String = "Sao Paulo Coffee Shop"

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
            TypeCoffee.HOUSE ->{
                val h = House()
                h.make(extra)
            }
        }
    }

    override fun cleanTables(number: Int) {
        listOfTables[number] = false
    }

    override fun setOnTable(number: Int) {
        listOfTables[number] = true
    }

    override fun createASpecial(): String {
        return  """   
           Ingredientes ESPECIAL Coffee:
           Leite
           Café com grãos moidos da ilha de java
           ganulados de papiori
           
       """.trimIndent()
    }

}

