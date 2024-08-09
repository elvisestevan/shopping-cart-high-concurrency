package com.elvisestevan.shoppingcart.resources.database.repository

import com.elvisestevan.shoppingcart.domain.entity.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.ProductReservationRepository
import com.elvisestevan.shoppingcart.resources.database.entity.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.ProductReservationJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository
import com.elvisestevan.shoppingcart.resources.database.entity.ProductReservation as ProductReservationEntity

@Repository
class ProductReservationRepositoryImpl(
    private val productReservationJPARepository: ProductReservationJPARepository,
) : ProductReservationRepository {
    @Observed
    override fun save(productReservation: ProductReservation): ProductReservation =
        productReservationJPARepository.save(ProductReservationEntity.fromDomain(productReservation)).toDomain()
}
