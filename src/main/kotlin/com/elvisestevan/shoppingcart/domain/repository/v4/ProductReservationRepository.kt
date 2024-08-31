package com.elvisestevan.shoppingcart.domain.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation

interface ProductReservationRepository {
    fun save(productReservation: ProductReservation): ProductReservation
}
