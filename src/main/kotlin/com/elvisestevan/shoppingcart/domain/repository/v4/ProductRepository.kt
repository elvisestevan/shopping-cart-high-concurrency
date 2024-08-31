package com.elvisestevan.shoppingcart.domain.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.Product

interface ProductRepository {
    fun findAll(): List<Product>

    fun findById(id: String): Product

    fun save(product: Product): Product
}
