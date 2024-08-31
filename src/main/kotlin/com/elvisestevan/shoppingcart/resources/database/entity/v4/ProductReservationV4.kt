package com.elvisestevan.shoppingcart.resources.database.entity.v4

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_reservation_v4")
data class ProductReservationV4(
    @Id
    @Column
    val id: String,
    @JoinColumn(name = "product_id")
    @ManyToOne
    val productV4: ProductV4,
    @Column
    val quantity: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(productReservation: com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation) =
            ProductReservationV4(
                id = productReservation.id,
                productV4 = ProductV4.fromDomain(productReservation.product),
                quantity = productReservation.quantity,
                createdAt = productReservation.createdAt,
            )
    }
}

fun ProductReservationV4.toDomain() =
    com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation(
        id = this.id,
        product = this.productV4.toDomain(),
        quantity = this.quantity,
        createdAt = this.createdAt,
    )
