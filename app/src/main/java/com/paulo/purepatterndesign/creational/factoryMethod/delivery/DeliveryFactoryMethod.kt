package com.paulo.scriptclass.designPattern.delivery

import android.app.job.JobInfo

abstract class Logistics {
    abstract fun createTransport(): Transport
    abstract fun delivery()
}

class MotorcycleDelivery(
    private val driversName: String,
    private val plateMotorcycle: String,
    private val hourToDelivery: String,
    private val hourFinishJobInfo: JobInfo? = null
) : Logistics() {


    override fun delivery() {
        println(
            """
            Start to deliver
            Hour : $hourToDelivery
            Hour to finish job info: ${hourFinishJobInfo ?: "--"}
            About Transport: Motorcycle
            ${createTransport().aboutDelivery()}
        """.trimIndent()
        )
    }

    override fun createTransport(): Transport {
        return Motorcycle(plate = plateMotorcycle, driversName = driversName)
    }

}

class BicycleDelivery(
    private val driversName: String,
    private val model: String,
    private val hourToDelivery: String,
    private val hourFinishJobInfo: JobInfo? = null
) : Logistics() {


    override fun delivery() {
        println(
            """
            Hour to delivery: $hourToDelivery
            Hour to finish job info: ${hourFinishJobInfo ?: "--"}
            About Transport: 
            ${createTransport().aboutDelivery()}
            Model:: $model
        """.trimIndent()
        )
    }

    override fun createTransport(): Transport {
        return Bicycle(model = model, driversName = driversName)
    }

}

interface Transport {
    fun aboutDelivery()
}

data class Motorcycle(
    val plate: String,
    val driversName: String
) : Transport {
    override fun aboutDelivery() {
        println(
            """
            Name $driversName
            Ipva::  12/12/2015
            Warning: VENCIDO!!
            About Transport: Bicycle
            Plate:: $plate
        """.trimIndent()
        )
    }
}

data class Bicycle(
    val model: String,
    val driversName: String
) : Transport {
    override fun aboutDelivery() {
        println(
            """
            Name $driversName
            About Transport: Bicycle
            Model:: $model
        """.trimIndent()
        )
    }
}

