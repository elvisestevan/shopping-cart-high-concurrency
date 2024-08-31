package com.elvisestevan.shoppingcart.resources.database.entity.v1

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import com.elvisestevan.shoppingcart.domain.entity.v1.Product as ProductDomain

@Entity
@Table(name = "product_v1")
data class ProductV1(
    @Id
    @Column
    val id: String,
    @Column
    val name: String,
    @Column
    val description: String,
    @Column
    val totalAvailableInStock: Int,
    @Column
    val totalInStock: Int,
) {
    companion object {
        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.v1.Product) =
            ProductV1(
                id = product.id,
                name = product.name,
                description = product.description,
                totalInStock = product.totalInStock,
                totalAvailableInStock = product.totalAvailableInStock,
            )
    }
}

fun ProductV1.toDomain() =
    ProductDomain(
        id = this.id,
        name = this.name,
        description = this.description,
        totalInStock = this.totalInStock,
        totalAvailableInStock = this.totalAvailableInStock,
    )