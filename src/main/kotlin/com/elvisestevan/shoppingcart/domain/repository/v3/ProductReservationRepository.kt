package com.elvisestevan.shoppingcart.domain.repository.v3

import com.elvisestevan.shoppingcart.domain.entity.v3.ProductReservation

interface ProductReservationRepository {
    fun save(productReservation: ProductReservation): ProductReservation
}