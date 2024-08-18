package com.elvisestevan.shoppingcart.domain.repository

import com.elvisestevan.shoppingcart.domain.entity.Product
import com.elvisestevan.shoppingcart.domain.entity.ProductReservation

interface ProductStockRepository {
    fun makeReservationInStock(
        productReservation: ProductReservation,
        product: Product,
        quantity: Int,
    ): Int
}
