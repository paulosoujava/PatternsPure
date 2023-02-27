package com.paulo.purepatterndesign

class PersonWithoutDsl{
    var name = ""
    var age = 0
    var address = AddressWithoutDsl()
}
class AddressWithoutDsl {
    var city = ""
    var street = ""
}



class AddressDto {
    var city = ""
    var street = ""
}

class PersonDto {
    var name = ""
    var age = 0
    var address = AddressDto()

    fun address(init: AddressDto.() -> Unit) {
        val address = AddressDto()
        address.init()
        this.address = address
    }
}


fun person(init: PersonDto.() -> Unit): PersonDto {
    val person = PersonDto()
    person.init()
    return person
}
fun main() {
    val person = person{
       this.name= "John"
        age = 18
        address {
            city = "New York"
            street = "Main Street"
        }
    }
    val person2 = PersonWithoutDsl().apply {
        name = "John"
        age = 18
        address.apply {
            city = "New York"
            street = "Main Street"
        }
    }
    val person3 = PersonWithoutDsl().let {
        it.name = "John"
        it.age = 18
        it.address.let {ad ->
            ad.city = "New York"
            ad.street = "Main Street"
        }
    }
}

















