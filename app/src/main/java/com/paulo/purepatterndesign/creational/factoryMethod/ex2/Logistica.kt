package com.paulo.purepatterndesign.creational.factoryMethod.ex2



/*
Vamos ao problema de logistic
Uma empresa chamada Mais Fast que faz entregas SOMENTE
vai terrestre com seus caminhÕes modernos, resolveu inovar
e agora passam a entregar de navio e também de patins,
mas para isso eles precisaram implementar os novos meios
de entrega.
Todo o código da empresa se resume em uma classe GOD
Eis o código legado:
 */

class Caminhao(pedagio: Long = 0L) {

    fun planoDeEntega() {
        print("Complexo calculo para entrega via terrestre com o pedagio de: ${planoDeEntega()}")
    }
    //more functions
}

/*
Como todo o código estava dentro de caminhão percisamos melhorar
isto, vamos iniciar criando uma interface transporte e
os futuros entregadores,classes concreta, da qual caminhao será rescrita
 */
interface Transporte {
    fun calculoDeTaxas()
}

class NewClassCaminhao(
    val pedagio: Long = 0L
) : Transporte {
    override fun calculoDeTaxas() {
        println("Complexo calculo para entrega via terrestre. Valor pedagio: $pedagio")
    }
}

class Navio(
    val taxasNoPorto: Long = 0L
) : Transporte {
    override fun calculoDeTaxas() {
        println("Complexo calculo para entrega via maritima. Valor taxa: $taxasNoPorto")
    }
}

class Patins(
    val bateriaPraOdiaTodo: Boolean = false,
) : Transporte {
    override fun calculoDeTaxas() {
        println("Complexo calculo para entrega via rodinhas. Precisa de bateria para o dia todo: $bateriaPraOdiaTodo")
    }
}

abstract class Entrega {
    abstract fun create(rota: String, transport: Transporte): Transporte
}

/*
Ok, ficou lindo agora vamos para as fabricas
 */
class EntregaMaritima : Entrega() {
    override fun create(rota: String, transport: Transporte): Transporte {
        return when (rota) {
            "ROTA_A" -> Navio(taxasNoPorto = 200L)
            "ROTA_B" -> Navio(taxasNoPorto = 100L)
            else -> {
                throw IllegalArgumentException("Error code")
            }
        }
    }
}

class EntregaTerrestre : Entrega() {
    override fun create(rota: String, transport: Transporte): Transporte {
        return when (rota) {
            "ROTA_A" -> NewClassCaminhao(pedagio = 200L)
            "ROTA_B" -> NewClassCaminhao(pedagio = 100L)
            else -> {
                throw IllegalArgumentException("Error code")
            }
        }
    }
}

class EntregaRodinhas : Entrega() {
    override fun create(rota: String, transport: Transporte): Transporte {
        return when (rota) {
            "ROTA_A" -> Patins(bateriaPraOdiaTodo = true)
            "ROTA_B" -> Patins()
            else -> {
                throw IllegalArgumentException("Error code")
            }
        }
    }
}


//Apartir de agora podemos usar assim:
fun main() {
    var transporteMaritimo = EntregaMaritima()
    val navioA = transporteMaritimo.create("ROTA_A", Navio())
    val navioB = transporteMaritimo.create("ROTA_B", Navio())

    navioA.calculoDeTaxas()
    navioB.calculoDeTaxas()

    val transportTerrestre = EntregaTerrestre()
    val caminhaYZ = transportTerrestre.create("ROTA_A", NewClassCaminhao())
    val caminhaXYZ = transportTerrestre.create("ROTA_B", NewClassCaminhao())

    caminhaYZ.calculoDeTaxas()
    caminhaXYZ.calculoDeTaxas()

    val transportDeRodinhas = EntregaRodinhas()
    val rodaYZ = transportDeRodinhas.create("ROTA_A", Patins())
    val rodaXYZ = transportDeRodinhas.create("ROTA_B", Patins())

    rodaYZ.calculoDeTaxas()
    rodaXYZ.calculoDeTaxas()
}

/*
Obviamente que para cada classe fabrica tais como: EntregaRodinhas, EntregaTerrestre, EntregaMaritima
pode se haver muito mais coisas especificas de cada montagem
 */