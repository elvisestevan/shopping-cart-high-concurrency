package com.elvisestevan.shoppingcart.application.web.dto.response

import com.elvisestevan.shoppingcart.domain.entity.Product
import java.time.LocalDateTime

class ProductResponse(
    val id: String,
    val name: String,
    val description: String,
    val totalAvailableInStock: Int,
    val totalInStock: Int,
) {
    companion object {
        fun fromDomain(product: Product) =
            ProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
                totalInStock = product.totalInStock,
                totalAvailableInStock = product.totalAvailableInStock,
            )
    }
}
