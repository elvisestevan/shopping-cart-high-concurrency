package com.elvisestevan.shoppingcart.domain.repository

import com.elvisestevan.shoppingcart.domain.entity.ProductReservation

interface ProductReservationRepository {
    fun save(productReservation: ProductReservation): ProductReservation
}
