package com.paulo.purepatterndesign.creational.abstractFactory

import com.paulo.purepatterndesign.creational.factoryMethod.ex1.BoletoXVideo

interface Multa {
    fun getMulta(): Float
}

interface Juros {
    fun getJuros(): Float
}

interface Desconto {
    fun getDesconto(): Float
}


class Boleto(private var valor: Float, calculoFactory: CalculoFactory) {

    private var juros: Juros
    private var desconto: Desconto
    private var multa: Multa

    init {
        this.juros = calculoFactory.criarJuros()
        this.desconto = calculoFactory.criarDesconto()
        this.multa = calculoFactory.criarMulta()
    }


    fun calculaJuros() = valor * juros.getJuros()
    fun calculaDesconto() = valor * desconto.getDesconto()
    fun calculaMulta() = valor * multa.getMulta()
}

class Banco {
    fun gerarBoleto(valor: Float, calculoFactory: CalculoFactory): Boleto {
        val boleto = Boleto(valor, calculoFactory)

        println(
            """
            Valor: $valor
            Juros: ${boleto.calculaJuros()}
            Desconto: ${boleto.calculaDesconto()}
            Multa: ${boleto.calculaMulta()}
            """
        )
        return boleto
    }


}

interface CalculoFactory {
    fun criarJuros(): Juros
    fun criarMulta(): Multa
    fun criarDesconto(): Desconto

}

//****
// CLASSES CONCRETAS -> FAMILIAS DO BB -> JUROS DESCONTO E MULTA
// PORÉM CALCULOS FACTORY NAO CONHECE ESTAS CLASSES E SIM ABSTRAÇAOES
// DELAS, OU SEJA SUAS INTERFACES
//****

class BBJuros : Juros {
    override fun getJuros() = 0.03F
}

class BBDesconto : Desconto {
    override fun getDesconto() = 0.05F
}

class BBMuta : Multa {
    override fun getMulta() = 0.08F
}
// FACTORY
//PROGRAMA POR INTERFACES, NAO PRECISA CONNHECER A CLASSE CONCRETA
// E SEUS CLACULOS, EX:
// ELA NAO CONHECE A CLASSE JUROS E SIM A INTERFACE JUTOS
// ELA NAO CONHECE A CLASSE DESCONTO E SIM A INTERFACE DESCONTO
// ELA NAO CONHECE A CLASSE MULTA E SIM A INTERFACE MULTA

class BBCalculoFactory : CalculoFactory {
    override fun criarJuros(): Juros {
        return BBJuros()
    }

    override fun criarMulta(): Multa {
        return BBMuta()
    }

    override fun criarDesconto(): Desconto {
        return BBDesconto()
    }

}

//ITAU
class ItauJuros : Juros {
    override fun getJuros() = 0.01F
}

class ItauDesconto : Desconto {
    override fun getDesconto() = 0.09F
}

class ItauMuta : Multa {
    override fun getMulta() = 0.12F
}

class ItauCalculoFactory : CalculoFactory {
    override fun criarJuros(): Juros {
        return ItauJuros()
    }

    override fun criarMulta(): Multa {
        return ItauMuta()
    }

    override fun criarDesconto(): Desconto {
        return ItauDesconto()
    }

}
fun main() {
    val banco = Banco()

    println("BANCO BB")
    banco.gerarBoleto(100F, BBCalculoFactory())
    println("BANCO ITAU")
    banco.gerarBoleto(200f, ItauCalculoFactory())
}