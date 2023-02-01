package com.paulo.purepatterndesign.creational
/*
     Resolve o problema de CRIAR objetos de produtos sem especificar a sua classe concreta
     Complexidade : BAIXA
     Popularidade: ALTA

     No examplo : Boletos
     O Problema: A empresas XVideos que atualmente trabalha com um unico banco o SANTO ANDRE e esta querendo
     evoluir e e para isto optou em contratar vc, o mestre dos mestres em design pattern para
     projetar o novo código de uma forma que nunca mais precise te chamar, caso queiram usar
     outros bancos.
     Eis o codigo legado:
 */


abstract class BoletoXVideo {
    abstract val valor: Float
    open var juros: Float = 0f
    open var desconto: Float = 0f
    open var multa: Float = 0f


     fun calculaJuros() = valor * juros
     fun calculaDesconto() = valor * juros
     fun calculaMulta() = valor * juros
}

class BancoSantoAndre10Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.12f
    override var desconto: Float = 0.3f
    override var multa: Float = 0.10f
}

class BancoSantoAndre20Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.22f
    override var desconto: Float = 0.2f
    override var multa: Float = 0.20f
}

class BancoSantoAndre30Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.32f
    override var desconto: Float = 0.1f
    override var multa: Float = 0.30f
}

class BancoSantoAndre {
    fun gerarBoleto(vencimento: Int, valor: Float): BoletoXVideo {
        val boleto = when (vencimento) {
            10 -> BancoSantoAndre10Dias(valor)
            20 -> BancoSantoAndre20Dias(valor)
            30 -> BancoSantoAndre30Dias(valor)
            else -> throw IllegalArgumentException("Vencimento invalido")
        }
        println(
            """
                Boleto gerado com sucesso no valor de R$ $valor
                Valor Juros: R$ ${boleto.calculaJuros()} 
                Valor Desconto: R$ ${boleto.calculaDesconto()}
                Valor Multa: R$ ${boleto.calculaMulta()} 
                """
        )
        return  boleto
    }
}


/*
Após  quase ter um infarto ao ver o valor do juros e a multa, vc se recompõe tomando
seu energético, vai no curso: https://designpatterns.com.br/ e checa na conclusão
que eles estão usando o simple Factory, logo, você vai aplicar então o Factory Method
    também conhecido como: Método fábrica, Construtor virtual

    Chegamos as seguintes conclusões, para cada BANCO, teremos X possibilidade de Boletos
    Ex: Banco do Zé opera com Boletos de 10 e 20 dias, Banco da ANA opera com Boletos de
    10 20 30 60 dias e cada um com seu juros e multa
    sabendo disto, faremos:
 */


abstract class Banco {
    abstract fun criarBoleto(vencimento: Int, valor: Float): BoletoXVideo
    fun gerarBoleto(vencimento: Int, valor: Float): BoletoXVideo {
        return criarBoleto(vencimento, valor)
    }
}

/*
E com isso podemos ter nossas Fabricas que constroem seus objetos
 */
class BancoDaAna10Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.12f
    override var desconto: Float = 0.3f
    override var multa: Float = 0.10f
}

class BancoDaAna20Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.22f
    override var desconto: Float = 0.2f
    override var multa: Float = 0.20f
}

class BancoDaAna30Dias(override val valor: Float) : BoletoXVideo() {
    override var juros: Float = 0.32f
    override var desconto: Float = 0.1f
    override var multa: Float = 0.30f
}

class BancoDaAna : Banco() {
    override fun criarBoleto(vencimento: Int, valor: Float): BoletoXVideo {
        val boleto =  when (vencimento) {
            10 -> BancoDaAna10Dias(valor)
            20 -> BancoDaAna20Dias(valor)
            30 -> BancoDaAna30Dias(valor)
            else -> throw IllegalArgumentException("Vencimento invalido")
        }
        println(
            """
                Boleto gerado com sucesso no valor de R$ $valor
                Valor Juros: R$  ${boleto.calculaJuros()} 
                Valor Desconto: R$ ${boleto.calculaDesconto()}
                Valor Multa: R$ ${boleto.calculaMulta()} 
                """
        )
        return  boleto
    }
}

/*
E com isso a classe legada :BancoSantoAndre acima teria que adapatar para
 */
class NovaClasseSantoAndre : Banco() {
    override fun criarBoleto(vencimento: Int, valor: Float): BoletoXVideo {
        val boleto = when (vencimento) {
            10 -> BancoSantoAndre10Dias(valor)
            20 -> BancoSantoAndre20Dias(valor)
            30 -> BancoSantoAndre30Dias(valor)
            else -> throw IllegalArgumentException("Vencimento invalido")
        }
        println(
                """
                Boleto gerado com sucesso no valor de R$ $valor
                Valor Juros: R$' ${boleto.calculaJuros()} 
                Valor Desconto: R$' ${boleto.calculaDesconto()}
                Valor Multa: R$'  ${boleto.calculaMulta()} 
                """
        )
        return  boleto
    }
}
/*
    Perceba que não mexemos nas classes de boletos do banco santo andre:
     BancoSantoAndre10Dias
     BancoSantoAndre20Dias
     BancoSantoAndre30Dias
     apenas ajustamos para que a classe BancoSantoAndre aceite a classe abstrata Banco

 usage:
 */

fun main() {
    //LEGADO
    val legadoBanco = BancoSantoAndre()
    legadoBanco.gerarBoleto(10, 100f)
    legadoBanco.gerarBoleto(20, 100f)
    legadoBanco.gerarBoleto(30, 100f)

    //FACTORY METHODS
    val factoryBanco = BancoDaAna()
    factoryBanco.gerarBoleto(10, 100f)// factory method
    factoryBanco.gerarBoleto(20, 100f)// factory method
    factoryBanco.gerarBoleto(30, 100f)// factory method

    val banco = NovaClasseSantoAndre()
    banco.gerarBoleto(10, 100f)// factory method
    banco.gerarBoleto(20, 100f)// factory method
    banco.gerarBoleto(30, 100f)// factory method

}
/*
E assim terminamos nosso trabalho de uma forma limpa e transparent
 */