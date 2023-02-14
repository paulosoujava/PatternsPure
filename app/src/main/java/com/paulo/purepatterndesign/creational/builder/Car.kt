package com.paulo.purepatterndesign.creational.builder


enum class CarType {
    CLASSIC,
    SPORT,
    BASIC
}

/*
A interface define todas as maneiras possiveis
para configurar o produto
 */
interface Builder {
    fun setCarType(carType: CarType)
    fun setSeat(seat: Int)
    fun hasGps(boolean: Boolean)
    fun hasTripComputer(boolean: Boolean)
    fun manualTrasmition(boolean: Boolean)
    //nao retornaremos um objeto carro pois queremos
    //utizar o mesmo builder para gerar um manual do carro
    // que tem as mesmas funcoes acima, porem retorna um MANUAL
    //fun getResult(): Car
}

/*
Builders concretos implementam o passos definidos
na interface acima
 */

class CarBuilder : Builder {
    private lateinit var carType: CarType
    private var seat: Int = 0
    private var hasGps: Boolean = false
    private var hasTripComputer: Boolean = false
    private var manualTrasmition: Boolean = false


    override fun setCarType(carType: CarType) {
        this.carType = carType
    }

    override fun setSeat(seat: Int) {
        this.seat = seat
    }

    override fun hasGps(boolean: Boolean) {
        this.hasGps = boolean
    }

    override fun hasTripComputer(boolean: Boolean) {
        this.hasTripComputer = boolean
    }

    override fun manualTrasmition(boolean: Boolean) {
        this.manualTrasmition = boolean
    }

    fun getResult(): Car {
        return Car(carType, seat, hasGps, hasTripComputer, manualTrasmition)
    }

}

/*
PAra construir o manual de um carro usasmos os mesmos possos
da interface builde
 */
class CarManualBuilder : Builder {
    private lateinit var carType: CarType
    private var seat: Int = 0
    private var hasGps: Boolean = false
    private var hasTripComputer: Boolean = false
    private var manualTrasmition: Boolean = false


    override fun setCarType(carType: CarType) {
        this.carType = carType
    }

    override fun setSeat(seat: Int) {
        this.seat = seat
    }

    override fun hasGps(boolean: Boolean) {
        this.hasGps = boolean
    }

    override fun hasTripComputer(boolean: Boolean) {
        this.hasTripComputer = boolean
    }

    override fun manualTrasmition(boolean: Boolean) {
        this.manualTrasmition = boolean
    }

    fun getResult(): CarManual {
        return CarManual(carType, seat, hasGps, hasTripComputer, manualTrasmition)
    }

}

class CarManual(
    val carType: CarType,
    val seat: Int,
    val hasGps: Boolean,
    val hasTripComputer: Boolean,
    val manualTrasmition: Boolean
) {
    fun manual(): String {
        return """
        Type of car ${carType}
        Count of seat ${seat}
        Transmission ${manualTrasmition}
        Has GPS ${hasGps}
        Has Computer Trip ${hasTripComputer}
        """
    }
}

class Car(
    val carType: CarType,
    val seat: Int,
    val hasGps: Boolean,
    val hasTripComputer: Boolean,
    val manualTrasmition: Boolean
){
    override fun toString(): String {
        return  """
            CAR::
        Type of car ${carType}
        Count of seat ${seat}
        Transmission ${manualTrasmition}
        Has GPS ${hasGps}
        Has Computer Trip ${hasTripComputer}
        """
    }
}

/*
O diretor define a order que sera construido
 */
class Director {
    fun buildSport(builder: Builder) {
        builder.hasTripComputer(true)
        builder.hasGps(true)
        builder.manualTrasmition(true)
        builder.setSeat(4)
        builder.setCarType(CarType.SPORT)
    }

    fun buildBasic(builder: Builder) {
        builder.hasTripComputer(false)
        builder.hasGps(false)
        builder.manualTrasmition(true)
        builder.setSeat(4)
        builder.setCarType(CarType.BASIC)
    }

    fun buildClassic(builder: Builder) {
        builder.hasTripComputer(false)
        builder.hasGps(false)
        builder.manualTrasmition(false)
        builder.setSeat(2)
        builder.setCarType(CarType.CLASSIC)
    }
}

//USaGE
fun main() {
    val director = Director()
    val carBuilder = CarBuilder()
    director.buildBasic(carBuilder)

    val car = carBuilder.getResult()
    println(car.toString())


    val carMBuilder = CarManualBuilder()
    director.buildBasic(carMBuilder)

    val manual = carMBuilder.getResult()
    println(manual.manual())
}