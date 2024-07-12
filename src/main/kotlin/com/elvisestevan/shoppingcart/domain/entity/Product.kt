package com.elvisestevan.shoppingcart.domain.entity

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val totalAvailableInStock: Int,
    val totalInStock: Int,
)