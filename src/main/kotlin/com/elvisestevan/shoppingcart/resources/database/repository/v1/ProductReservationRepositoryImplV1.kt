package com.elvisestevan.shoppingcart.resources.database.repository.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1.ProductReservationJPARepositoryV1
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
class ProductReservationRepositoryImplV1(
    private val productReservationJPARepositoryV1: ProductReservationJPARepositoryV1,
) : ProductReservationRepository {
    @Observed
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepositoryV1.save(com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductReservationV1.fromDomain(productReservation)).toDomain()
}