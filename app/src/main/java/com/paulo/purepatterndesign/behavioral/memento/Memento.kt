package com.paulo.scriptclass.designPattern.memento

interface Memento<T> {
    fun saveHistory(memento: T)
    fun undoMemento(): T?
}

class Caretaker {
    private val history = mutableListOf<Menu>()

    fun addHistory(memento: Menu) {
        if (!history.contains(memento))
            history.add(memento)
    }
    fun undoHistory(): Menu? {
        if (history.isNotEmpty() && history.size > 1) {
            history.removeAt(history.size - 1)
            return history.last()
        }
        return null
    }
}

class Snapshot(val caretaker: Caretaker) : Memento<Menu> {

    //private val caretaker = Caretaker()

    override fun saveHistory(memento: Menu) {
        println("Menu Actual: ${memento.print()}")
        caretaker.addHistory(memento)
    }

    override fun undoMemento(): Menu? {
        val snapshot = caretaker.undoHistory()
        if (snapshot != null) {
            println("Back to : ${snapshot.print()}")
        }

        return snapshot
    }
}

data class ItemMenu(
    val iconItemMenu: String,
    val labelItemMenu: String
){
    override fun toString(): String {
        return labelItemMenu
    }
}

class Menu(
    private val labelOfMenu: String,
    private val listItem: List<ItemMenu>
) {
    fun print() = labelOfMenu + listItem.toString()

}

fun main() {

    val menuHome = Menu(
        "Home", listOf(
            ItemMenu("iconSetting", "Settings"),
            ItemMenu("iconFavorite", "Favorite"),
            ItemMenu("iconLogout", "Logout"),
        )
    )
    val menuSettings = Menu(
        "Settings", listOf(
            ItemMenu("iconSetting", "NOTIFICATION"),
            ItemMenu("some icon", "some item"),
            ItemMenu("some icon", "some item"),
            ItemMenu("some icon", "some item"),
        )
    )
    val menuFavorite = Menu(
        "Favorite", listOf(
            ItemMenu("iconFavorite", "Meus itens"),
            ItemMenu("some icon", "some item"),
            ItemMenu("some icon", "some item"),
            ItemMenu("some icon", "some item"),
        )
    )


    val snapshot = Snapshot(Caretaker())
    snapshot.saveHistory(menuHome)
    snapshot.saveHistory(menuFavorite)
    snapshot.undoMemento()
    snapshot.saveHistory(menuSettings)


}

