package com.elvisestevan.shoppingcart.resources.database.repository.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1.ProductReservationJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
class ProductReservationRepositoryImpl(
    private val productReservationJPARepository: ProductReservationJPARepository,
) : ProductReservationRepository {
    @Observed
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepository.save(com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductReservation.fromDomain(productReservation)).toDomain()
}