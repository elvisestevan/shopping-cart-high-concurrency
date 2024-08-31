package com.elvisestevan.shoppingcart.domain.service.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.Product
import com.elvisestevan.shoppingcart.domain.entity.v4.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductRepository
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductReservationRepository
import com.elvisestevan.shoppingcart.domain.repository.ProductStockRepository
import de.huxhorn.sulky.ulid.ULID
import io.micrometer.observation.annotation.Observed
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@Observed
class ProductServiceV4(
    private val productRepository: ProductRepository,
    private val productReservationRepository: ProductReservationRepository,
    private val productStockRepository: ProductStockRepository,
) {
    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(productId: String): Product = productRepository.findById(productId)

    @Transactional
    fun makeReservation(
        productId: String,
        quantity: Int,
    ): ProductReservation {
        val product = productRepository.findById(productId)
        val productReservationId = ULID().nextULID()

        val productReservation = productReservationRepository.save(ProductReservation(productReservationId, product, quantity))

        val totalReserved = productStockRepository.makeReservationInStock(productReservation, product, quantity)

        if (totalReserved < quantity) {
            throw ResponseStatusException(HttpStatusCode.valueOf(400), "Product not available in stock")
        }

        return productReservation
    }
}
