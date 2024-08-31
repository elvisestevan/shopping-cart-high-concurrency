package com.elvisestevan.shoppingcart.resources.database.entity.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_reservation_v1")
data class ProductReservationV1(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val product: ProductV1,
    @Column
    val quantity: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(productReservation: com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation) =
            ProductReservationV1(
                id = productReservation.id,
                product = ProductV1.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}

fun ProductReservationV1.toDomain() =
    com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation(
        id = this.id,
        product = this.product.toDomain(),
        quantity = this.quantity,
        createdAt = this.createdAt,
    )
