package com.elvisestevan.shoppingcart.domain.entity.v1

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val totalAvailableInStock: Int,
    val totalInStock: Int,
)