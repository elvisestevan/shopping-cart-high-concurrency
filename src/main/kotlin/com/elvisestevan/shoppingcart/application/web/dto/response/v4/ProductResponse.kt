package com.elvisestevan.shoppingcart.application.web.dto.response.v4

class ProductResponse(
    val id: String,
    val name: String,
    val description: String,
) {
    companion object {
        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.v4.Product) =
            ProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
            )

        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.v1.Product) =
            ProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
            )
    }
}
