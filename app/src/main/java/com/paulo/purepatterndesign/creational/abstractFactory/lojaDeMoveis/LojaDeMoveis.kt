package com.paulo.purepatterndesign.creational.abstractFactory.lojaDeMoveis

/*
  Uma loja de moveis produz moveis  : CADEIRA MESA e MESA DE CENTRO
  só que agora ela resolveu evoluir e para cada Cadeira
  existe um tipo:
   Cadeira -> Decorativa, Vitoriano e moderna
   Mesa -> Decorativa, Vitoriano e moderna
   Mesa de Centro > -> Decorativa, Vitoriano e moderna

1 - CRIAMOS AS ITERFACES DAS FAMILIAS
 */
interface Cadeira{
    fun podeSentar(): Boolean
    fun qtdPernas(): Int
}
interface Mesa{
    fun cabeQntsPessoa(): Int
    fun ehRendonda(): Boolean
}
interface MesaDeCentro{
    fun temGaveta():Boolean
    fun temRodinhas():Boolean
}

//2 -> CRIAMOS AS CLASSES CONCRETAS QUE IMPLEMANTA O CONTRATO ACIMA
// PARA CADA FAMILA DE CADEIRA TODAS DEVEM IMPLEMENTAR O CONTRATO
//IMAGINA QUE VC FOSSE CRIAR A Class CadeiraFuturista voce obrigatóriamente
// teria que implementar o interface Cadira

// FAMILIA CADEIRA`
class CadeiraVitoriano: Cadeira{
    override fun podeSentar()=false
    override fun qtdPernas()=1
}
class CadeiraModerna: Cadeira{
    override fun podeSentar()=true
    override fun qtdPernas()=4
}
class CadeiraDecorativa: Cadeira{
    override fun podeSentar()=true
    override fun qtdPernas()=3
}
// FAMILIA MESA`
class MesaVitoriano: Mesa{
    override fun cabeQntsPessoa()=4
    override fun ehRendonda()=true
}
class MesaDecorativa: Mesa{
    override fun cabeQntsPessoa()=0
    override fun ehRendonda()=false
}
class MesaModerna: Mesa{
    override fun cabeQntsPessoa()=6
    override fun ehRendonda()=true
}
// FAMILIA MESA`DE CENTRO
class MesaDeCentroVitoriano: MesaDeCentro{
    override fun temGaveta()= true
    override fun temRodinhas()= true
}
class MesaDeCentroModerna: MesaDeCentro{
    override fun temGaveta()=false
    override fun temRodinhas()=true
}
class MesaDeCentroDecorativa: MesaDeCentro{
    override fun temGaveta()=false
    override fun temRodinhas()=false
}

//INTERFACE DAS FARBICARS PARA CRIAR FAMILIAS

interface  Fabrica{
    fun criaCadeira():Cadeira
    fun criaMesa():Mesa
    fun criaMesaDeCentro():MesaDeCentro
}
// TODAS AS FAMILIAS DECORATIVAS DE CADEIRA, MESA E MESA DE CENTRO
class DecorativaFabric: Fabrica{
    override fun criaCadeira(): Cadeira {return CadeiraDecorativa()}
    override fun criaMesa(): Mesa {return MesaDecorativa()}
    override fun criaMesaDeCentro(): MesaDeCentro {return MesaDeCentroDecorativa()}
}
// TODAS AS FAMILIAS MODERNA DE CADEIRA, MESA E MESA DE CENTRO
class ModernaFabric: Fabrica{
    override fun criaCadeira(): Cadeira {return CadeiraModerna()}
    override fun criaMesa(): Mesa {return MesaModerna()}
    override fun criaMesaDeCentro(): MesaDeCentro {return MesaDeCentroModerna()}
}
// TODAS AS FAMILIAS VITORIANA DE CADEIRA, MESA E MESA DE CENTRO
class VitorianaFabric: Fabrica{
    override fun criaCadeira(): Cadeira {return CadeiraVitoriano()}
    override fun criaMesa(): Mesa {return MesaVitoriano()}
    override fun criaMesaDeCentro(): MesaDeCentro {return MesaDeCentroVitoriano()}
}

fun main() {
    val decoMesa = MesaDecorativa()
    println("MESA DECORATIVA")
    println("Quantas pessoas sentam: ${decoMesa.cabeQntsPessoa()}")
    println("A Mesa é redonda: ${decoMesa.ehRendonda()}")

    println("MESA MODERNA")
    val moderMesa = MesaModerna()
    println("Quantas pessoas sentam: ${moderMesa.cabeQntsPessoa()}")
    println("A Mesa é redonda: ${moderMesa.ehRendonda()}")

    println("MESA VITORIANA")
    val vicMesa = MesaVitoriano()
    println("Quantas pessoas sentam: ${vicMesa.cabeQntsPessoa()}")
    println("A Mesa é redonda: ${vicMesa.ehRendonda()}")


    val cadMod = CadeiraModerna()
    println("Cadeira DECORATIVA")
    println("Pode sentar: ${cadMod.podeSentar()}")
    println("Tem quantas pernas: ${cadMod.qtdPernas()}")

    val cadVit = CadeiraVitoriano()
    println("Cadeira Vitoriana")
    println("Pode sentar: ${cadVit.podeSentar()}")
    println("Tem quantas pernas: ${cadVit.qtdPernas()}")

    println("Cadeira Moderna")
    val cadModer = CadeiraModerna()
    println("Pode sentar: ${cadModer.podeSentar()}")
    println("Tem quantas pernas: ${cadModer.qtdPernas()}")


    println("Mesa de centro Moderna")
    val m1 = MesaDeCentroModerna()
    println("Tem gaveta: ${m1.temGaveta()}")
    println("Tem rodinhas: ${m1.temRodinhas()}")

    println("Mesa de centro Vitoriana")
    val m2 = MesaDeCentroVitoriano()
    println("Tem gaveta: ${m2.temGaveta()}")
    println("Tem rodinhas: ${m2.temRodinhas()}")

    println("Mesa de centro Decorativa")
    val m3 = MesaDeCentroDecorativa()
    println("Tem gaveta: ${m3.temGaveta()}")
    println("Tem rodinhas: ${m3.temRodinhas()}")
}