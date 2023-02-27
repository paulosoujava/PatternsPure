package com.paulo.scriptclass.designPattern.cart.singleton

data class Product(
    val id: Int,
    val name: String,
    val qtd: Int,
    val price: Double
)

object Cart {


    private var list = mutableListOf<Product>()


    fun getTotalOfCart() = list.sumOf { it.price }
    fun getTotalItems() = list.sumOf { it.qtd }

    fun listOfProduct(): List<Product> {
        return list
    }

    fun addProduct(product: Product) {
        val indice = getIndice(product)
        if (indice >= 0) {
            val newProduct = list[indice]
            val qtd = newProduct.qtd + 1
            val price = product.price * qtd
            list.removeAll { it.id == product.id }
            list.add(Product(newProduct.id, newProduct.name, qtd, price))
            return
        }
        list.add(product)
    }

    fun removeProduct() {
        list = mutableListOf()
    }

    fun decrementProduct(product: Product) {
        val indice = getIndice(product)

        if (indice >= 0) {

            if (list[indice].qtd == 1) {
                list.removeAt(indice)
                return
            }
            val price = list[indice].price
            val qtd = list[indice].qtd - 1
            val updateProduct = list[indice].copy(qtd = qtd, price = (price - product.price))
            list.removeAt(indice)
            list.add(indice, updateProduct)
        }
    }

    private fun getIndice(product: Product) = list.indexOfFirst { it.id == product.id }
}