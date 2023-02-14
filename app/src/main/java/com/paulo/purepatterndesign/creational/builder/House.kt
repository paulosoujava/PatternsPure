package com.paulo.purepatterndesign.creational.builder



interface BuilderHouse {
    fun buildWalls()
    fun buildWindows()
    fun buildRoof()
    fun buildGarage()
    fun getResult(): House

}

class MakeHouseBuilder(
    private val house: House,
) : BuilderHouse {
    override fun buildWalls() {
        house.walls = "MADEIRA"
    }

    override fun buildWindows() {
        house.windows = 3
    }

    override fun buildRoof() {
        house.roof = true
    }

    override fun buildGarage() {
        house.garage = true
    }

    override fun getResult(): House {
        return house
    }

}

class House(
    var windows: Int = 0,
    var walls: String = "",
    var roof: Boolean = false,
    var garage: Boolean = false
)

class DirecorHouse(val builderHouse: MakeHouseBuilder) {
    fun makeBasic() : House {
        builderHouse.buildRoof()
        builderHouse.buildGarage()
        builderHouse.buildWalls()
        builderHouse.buildWindows()
        return builderHouse.getResult()
    }

    fun makeLuxe(): House {
        builderHouse.buildRoof()
        builderHouse.buildWalls()
        builderHouse.buildWindows()
        return builderHouse.getResult()
    }


}

fun main() {
    val house = House()
    val builderHouse = MakeHouseBuilder(house)


    val director = DirecorHouse(builderHouse)
    val basicHouse =director.makeBasic()
    val luxeHouse =director.makeLuxe()

    //or I make may house
    builderHouse.buildGarage()
    builderHouse.buildWindows()
    val myHouse = builderHouse.getResult()
}