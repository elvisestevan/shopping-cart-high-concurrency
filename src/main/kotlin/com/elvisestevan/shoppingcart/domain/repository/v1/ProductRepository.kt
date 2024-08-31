package com.elvisestevan.shoppingcart.domain.repository.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.Product

interface ProductRepository {
    fun findAll(): List<Product>

    fun findById(id: String): Product

    fun save(product: Product): Product
}