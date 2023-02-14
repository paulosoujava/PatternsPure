package com.paulo.scriptclass.designPattern.payments

/*
    PIX
    VA
    CREDIT CARD
 */
interface Payment {
    fun createPayment(value: Float)
}

data class CreditCard(
    val value: Float,
    val parcel: Int,
    val number: String,
    val date: String,
    val code: String
) : Payment {
    private var total: Float = 0f
    override fun createPayment(value: Float) {
        total = (value + (parcel * 0.2)).toFloat()
        print(
            """ 
            Method Payment = Credit Card
            Parcel = $parcel
             Number = $number
             Date = $date
             Code = $code
             Total = $total
        """.trimIndent()
        )
    }
}

data class Pix(
    val value: Float,
    val codeKeyPix: String,
    val expire: String
) : Payment {

    override fun createPayment(value: Float) {
        print(
            """ 
            Method Payment = PIX
             Code PIX = $codeKeyPix
             expire at = $expire
             Total = $value
        """.trimIndent()
        )
    }
}

enum class TypeVA(name: String) {
    ALIMENTACAO("ALIMENTACAO"),
    REFEICAO("REFEICAO")
}

data class VA(
    val typeVA: TypeVA,
    val value: Float,
    val number: String,
) : Payment {
    override fun createPayment(value: Float) {
        print(
            """ 
            Method Payment = VA
             Type = ${typeVA.name}
             number = $number
             Total = $value
        """.trimIndent()
        )
    }
}

