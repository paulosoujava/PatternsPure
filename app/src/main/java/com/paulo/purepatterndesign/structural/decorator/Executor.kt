package com.paulo.scriptclass.designPattern.decorator

fun main() {
    val beverage: Beverage = Espresso()
    System.out.println(
        beverage.getDescription()
                + " $" + beverage.cost()
    )

    var beverage2: Beverage = DarkRoast()
    beverage2 = Mocha(beverage2)
    beverage2 = Mocha(beverage2)
    beverage2 = Whip(beverage2)
    System.out.println(
        beverage2.getDescription()
                + " $" + beverage2.cost()
    )

    var beverage3: Beverage = HouseBlend()
    beverage3 = Soy(beverage3)
    beverage3 = Mocha(beverage3)
    beverage3 = Whip(beverage3)
    System.out.println(
        beverage3.getDescription()
                + " $" + beverage3.cost()
    )
}