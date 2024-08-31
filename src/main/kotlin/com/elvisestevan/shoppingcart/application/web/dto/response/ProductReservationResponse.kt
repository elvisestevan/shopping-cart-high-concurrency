package com.elvisestevan.shoppingcart.application.web.dto.response

import com.elvisestevan.shoppingcart.application.web.dto.response.v4.ProductResponse
import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation
import java.time.LocalDateTime

class ProductReservationResponse(
    val id: String,
    val product: ProductResponse,
    val quantity: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun fromDomain(productReservation: ProductReservation) =
            ProductReservationResponse(
                id = productReservation.id,
                product = ProductResponse.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}
