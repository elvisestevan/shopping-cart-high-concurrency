package com.elvisestevan.shoppingcart.domain.entity

import java.time.LocalDateTime

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val version: Long,
)
