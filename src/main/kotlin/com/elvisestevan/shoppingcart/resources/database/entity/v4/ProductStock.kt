package com.elvisestevan.shoppingcart.resources.database.entity.v4

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import com.elvisestevan.shoppingcart.domain.entity.v4.ProductStock as ProductStockDomain

@Entity
@Table(name = "product_stock")
data class ProductStock(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val productV4: ProductV4,
    @JoinColumn(name = "product_reservation_id", nullable = true)
    @ManyToOne
    val productReservationV4: ProductReservationV4? = null,
    @CreationTimestamp
    val createdAt: LocalDateTime = now(),
    @UpdateTimestamp
    val updatedAt: LocalDateTime = now(),
) {
    companion object {
        fun fromDomain(productStock: com.elvisestevan.shoppingcart.domain.entity.v4.ProductStock) =
            ProductStock(
                id = productStock.id,
                productV4 = ProductV4.fromDomain(productStock.product),
                productReservationV4 = productStock.productReservation?.let { ProductReservationV4.fromDomain(it) },
                createdAt = productStock.createdAt,
                updatedAt = productStock.updatedAt,
            )
    }
}

fun ProductStock.toDomain() =
    ProductStockDomain(
        id = this.id,
        product = this.productV4.toDomain(),
        productReservation = this.productReservationV4?.toDomain(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
