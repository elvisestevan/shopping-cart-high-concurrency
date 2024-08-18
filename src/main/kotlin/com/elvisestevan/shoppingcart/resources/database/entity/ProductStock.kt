package com.elvisestevan.shoppingcart.resources.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import com.elvisestevan.shoppingcart.domain.entity.ProductStock as ProductStockDomain

@Entity
@Table(name = "product_stock")
data class ProductStock(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val product: Product,
    @JoinColumn(name = "product_reservation_id", nullable = true)
    @ManyToOne
    val productReservation: ProductReservation? = null,
    @CreationTimestamp
    val createdAt: LocalDateTime = now(),
    @UpdateTimestamp
    val updatedAt: LocalDateTime = now(),
) {
    companion object {
        fun fromDomain(productStock: com.elvisestevan.shoppingcart.domain.entity.ProductStock) =
            ProductStock(
                id = productStock.id,
                product = Product.fromDomain(productStock.product),
                productReservation = productStock.productReservation?.let { ProductReservation.fromDomain(it) },
                createdAt = productStock.createdAt,
                updatedAt = productStock.updatedAt,
            )
    }
}

fun ProductStock.toDomain() =
    ProductStockDomain(
        id = this.id,
        product = this.product.toDomain(),
        productReservation = this.productReservation?.toDomain(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
