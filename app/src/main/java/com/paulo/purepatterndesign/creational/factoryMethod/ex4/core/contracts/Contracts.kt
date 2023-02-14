package com.paulo.scriptclass.designPattern.coffeeShop.abstractFactory.core.contracts

interface SpecialCoffee{
    fun  createASpecial(): String
}


interface Coffee {
    fun make(extras: String): String
}
