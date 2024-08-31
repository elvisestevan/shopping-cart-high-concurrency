package com.elvisestevan.shoppingcart.resources.database.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.Product
import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.ProductStockRepository
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.ProductStockJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
@Observed
class ProductStockRepositoryImpl(
    private val productStockJPARepository: ProductStockJPARepository,
) : ProductStockRepository {
    override fun makeReservationInStock(
        productReservation: ProductReservation,
        product: Product,
        quantity: Int,
    ): Int = productStockJPARepository.makeReservationInStock(productReservation.id, product.id, quantity)
}
