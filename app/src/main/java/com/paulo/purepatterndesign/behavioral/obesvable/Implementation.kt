package com.paulo.scriptclass.designPattern.obesvable

interface Subject{
    fun registerObservable(o: Observer )
    fun removeObservable(o: Observer )
    fun notifyObservable()
}

interface Observer{
    fun newInChannel():String
}

data class ChannelYoutube(
     val  listOfObervers: MutableList<Observer> = mutableListOf<Observer>()
):Subject{


    override fun registerObservable(o: Observer) {
        listOfObervers.add(o)

    }

    override fun removeObservable(o: Observer) {
        listOfObervers.remove(o)

    }

    override fun notifyObservable() {
        listOfObervers.forEach {
            println("  ${it.newInChannel()}")
        }
    }
}
class UserPaulo: Observer{ override fun newInChannel()= "Paulo "}
class UserRenata: Observer{ override fun newInChannel()= "Renata "}
class UserMalu: Observer{ override fun newInChannel()= "Malu "}
class UserBruna: Observer{ override fun newInChannel()= "Bruna "}


fun main() {
    val channelYoutube = ChannelYoutube()
    channelYoutube.registerObservable(UserPaulo())
    channelYoutube.registerObservable(UserRenata())
    channelYoutube.removeObservable(UserPaulo())
    channelYoutube.registerObservable(UserMalu())
    channelYoutube.registerObservable(UserBruna())
    println("\n\nREGISTRADOS: ")
    channelYoutube.notifyObservable()

    println("")
}