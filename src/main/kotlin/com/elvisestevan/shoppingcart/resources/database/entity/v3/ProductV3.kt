package com.elvisestevan.shoppingcart.resources.database.entity.v3

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import com.elvisestevan.shoppingcart.domain.entity.v3.Product as ProductDomain

@Entity
@Table(name = "product_v3")
data class ProductV3(
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
    @CreationTimestamp
    val createdAt: LocalDateTime = now(),
    @UpdateTimestamp
    val updatedAt: LocalDateTime = now(),
    @Version
    val version: Long,
) {
    companion object {
        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.v3.Product) =
            ProductV3(
                id = product.id,
                name = product.name,
                description = product.description,
                totalInStock = product.totalInStock,
                totalAvailableInStock = product.totalAvailableInStock,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                version = product.version,
            )
    }
}

fun ProductV3.toDomain() =
    ProductDomain(
        id = this.id,
        name = this.name,
        description = this.description,
        totalInStock = this.totalInStock,
        totalAvailableInStock = this.totalAvailableInStock,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        version = this.version,
    )