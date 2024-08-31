package com.elvisestevan.shoppingcart.resources.database.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v4.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4.ProductReservationJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
@Observed
class ProductReservationRepositoryImpl(
    private val productReservationJPARepository: ProductReservationJPARepository,
) : ProductReservationRepository {
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepository.save(com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductReservation.fromDomain(productReservation)).toDomain()
}
