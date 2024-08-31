package com.elvisestevan.shoppingcart.resources.database.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v4.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4.ProductReservationJPARepositoryV4
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
@Observed
class ProductReservationRepositoryImplV4(
    private val productReservationJPARepositoryV4: ProductReservationJPARepositoryV4,
) : ProductReservationRepository {
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepositoryV4.save(com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductReservationV4.fromDomain(productReservation)).toDomain()
}
