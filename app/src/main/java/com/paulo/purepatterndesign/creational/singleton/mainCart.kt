package com.paulo.scriptclass.designPattern.cart.singleton

fun main() {
    val cart = Cart
    val notebook = Product(1,"notebook Accer", price = 2.000, qtd = 1)
    val notebookDell = Product(4,"notebook DEll", price = 6.000, qtd = 1)
    val xbox = Product(2,"xbox", price = 1.000, qtd = 1)
    val mouse = Product(3,"mouse", price = 100.00, qtd = 1)


    Cart.addProduct(notebook)
    Cart.addProduct(notebook)
    Cart.addProduct(notebook)
    Cart.addProduct(notebook)
    Cart.addProduct(notebookDell)
    /*cart.decrementProduct(notebook)
    cart.decrementProduct(notebook)
    cart.decrementProduct(notebook)
    cart.decrementProduct(notebook)*/
    Cart.addProduct(xbox)
    Cart.addProduct(mouse)
    //cart.decrementProduct(notebook)
    //cart.removeProduct()

    println("Cart Valor Total:: ${Cart.getTotalOfCart()} Quantidade de items: ${Cart.getTotalItems()}")
    Cart.listOfProduct().forEach {
        println("${it.id} ${it.name} - ${it.price} - ${it.qtd}")
    }
}