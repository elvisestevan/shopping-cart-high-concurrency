package com.elvisestevan.shoppingcart.resources.database.repository.v3

import com.elvisestevan.shoppingcart.domain.entity.v3.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v3.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v3.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v3.ProductReservationJPARepositoryV3
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
class ProductReservationRepositoryImplV3(
    private val productReservationJPARepositoryV1: ProductReservationJPARepositoryV3,
) : ProductReservationRepository {
    @Observed
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepositoryV1.save(com.elvisestevan.shoppingcart.resources.database.entity.v3.ProductReservationV3.fromDomain(productReservation)).toDomain()
}