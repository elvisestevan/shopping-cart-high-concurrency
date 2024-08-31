package com.elvisestevan.shoppingcart.domain.repository

import com.elvisestevan.shoppingcart.domain.entity.v4.Product
import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation

interface ProductStockRepository {
    fun makeReservationInStock(
        productReservation: ProductReservation,
        product: Product,
        quantity: Int,
    ): Int
}
