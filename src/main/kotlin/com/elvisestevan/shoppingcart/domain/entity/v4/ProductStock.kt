package com.elvisestevan.shoppingcart.domain.entity.v4

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class ProductStock(
    val id: String,
    val product: Product,
    val productReservation: ProductReservation?,
    val createdAt: LocalDateTime = now(),
    val updatedAt: LocalDateTime = now(),
)
