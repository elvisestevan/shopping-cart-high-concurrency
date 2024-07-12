package com.elvisestevan.shoppingcart.resources.database.entity

import com.elvisestevan.shoppingcart.domain.entity.Product as ProductDomain
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class Product(

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
        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.Product) = Product(
            id = product.id,
            name = product.name,
            description = product.description,
            totalInStock = product.totalInStock,
            totalAvailableInStock = product.totalAvailableInStock,
        )
    }
}

fun Product.toDomain() = ProductDomain(
    id = this.id,
    name = this.name,
    description = this.description,
    totalInStock = this.totalInStock,
    totalAvailableInStock = this.totalAvailableInStock,
)