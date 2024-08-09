package com.elvisestevan.shoppingcart.resources.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_reservation")
data class ProductReservation(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val product: Product,
    @Column
    val quantity: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(productReservation: com.elvisestevan.shoppingcart.domain.entity.ProductReservation) =
            ProductReservation(
                id = productReservation.id,
                product = Product.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}

fun ProductReservation.toDomain() =
    com.elvisestevan.shoppingcart.domain.entity.ProductReservation(
        id = this.id,
        product = this.product.toDomain(),
        quantity = this.quantity,
        createdAt = this.createdAt,
    )
