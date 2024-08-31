package com.elvisestevan.shoppingcart.resources.database.entity.v4

import jakarta.persistence.*
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
        fun fromDomain(productReservation: com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation) =
            ProductReservation(
                id = productReservation.id,
                product = Product.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}

fun ProductReservation.toDomain() =
    com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation(
        id = this.id,
        product = this.product.toDomain(),
        quantity = this.quantity,
        createdAt = this.createdAt,
    )
