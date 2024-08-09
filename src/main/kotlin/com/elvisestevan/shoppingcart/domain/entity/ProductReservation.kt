package com.elvisestevan.shoppingcart.domain.entity

import java.time.LocalDateTime

data class ProductReservation(
    val id: String,
    val product: Product,
    val quantity: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
