package com.elvisestevan.shoppingcart.resources.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import com.elvisestevan.shoppingcart.domain.entity.Product as ProductDomain

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
    @CreationTimestamp
    val createdAt: LocalDateTime = now(),
    @UpdateTimestamp
    val updatedAt: LocalDateTime = now(),
    @Version
    val version: Long,
) {
    companion object {
        fun fromDomain(product: com.elvisestevan.shoppingcart.domain.entity.Product) =
            Product(
                id = product.id,
                name = product.name,
                description = product.description,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                version = product.version,
            )
    }
}

fun Product.toDomain() =
    ProductDomain(
        id = this.id,
        name = this.name,
        description = this.description,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        version = this.version,
    )
