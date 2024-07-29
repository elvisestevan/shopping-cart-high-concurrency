package com.elvisestevan.shoppingcart.domain.repository

import com.elvisestevan.shoppingcart.domain.entity.Product

interface ProductRepository {
    fun findAll(): List<Product>

    fun findById(id: String): Product

    fun save(product: Product): Product
}
