package com.elvisestevan.shoppingcart.domain.repository.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation

interface ProductReservationRepository {
    fun save(productReservation: ProductReservation): ProductReservation
}