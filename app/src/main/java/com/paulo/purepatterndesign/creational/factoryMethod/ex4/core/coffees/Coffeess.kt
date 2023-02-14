package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.coffees

import com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.contracts.Coffee



class Espresso : Coffee {
    override fun make(extras: String) =
        """   
           Ingredientes Espresso Coffee:
           Leite
           Café com grãos moidos da ilha de java
           ganulados de papiori
           Extras:
           $extras
       """.trimIndent()

}

class Cappiccino : Coffee {
    override fun make(extras: String) =
        """   
           Ingredientes Cappuccino Coffee::
           Leite
           Café com grãos moidos da ilha de java
           ganulados de papiori
           Extras:
           $extras
       """.trimIndent()
}

class House : Coffee {
    override fun make(extras: String) =
        """   
           Ingredientes House Coffee:
           Leite
           Café com grãos moidos da ilha de java
           ganulados de papiori
           Extras:
           $extras
       """.trimIndent()
}