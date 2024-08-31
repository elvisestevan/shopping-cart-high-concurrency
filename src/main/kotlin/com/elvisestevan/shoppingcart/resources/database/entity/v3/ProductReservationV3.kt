package com.elvisestevan.shoppingcart.resources.database.entity.v3

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_reservation_v3")
data class ProductReservationV3(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val product: ProductV3,
    @Column
    val quantity: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(productReservation: com.elvisestevan.shoppingcart.domain.entity.v3.ProductReservation) =
            ProductReservationV3(
                id = productReservation.id,
                product = ProductV3.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}

fun ProductReservationV3.toDomain() =
    com.elvisestevan.shoppingcart.domain.entity.v3.ProductReservation(
        id = this.id,
        product = this.product.toDomain(),
        quantity = this.quantity,
        createdAt = this.createdAt,
    )
