package com.elvisestevan.shoppingcart.domain.service.v2

import com.elvisestevan.shoppingcart.domain.entity.v1.Product
import com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductRepository
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductReservationRepository
import de.huxhorn.sulky.ulid.ULID
import io.micrometer.observation.annotation.Observed
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@Observed
class ProductServiceV2(
    private val productRepository: ProductRepository,
    private val productReservationRepositoryImplV1: ProductReservationRepository,
) {
    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(productId: String): Product = productRepository.findById(productId)

    @Transactional
    fun makeReservation(
        productId: String,
        quantity: Int,
    ): Product {
        val product = productRepository.findById(productId)
        if (product.totalAvailableInStock < quantity) {
            throw ResponseStatusException(
                HttpStatusCode.valueOf(500),
                "error on making reservation, total available is ${product.totalAvailableInStock} " +
                        "and you're trying to make a reservation of $quantity items",
            )
        }
        productReservationRepositoryImplV1.save(ProductReservation(ULID().nextULID(), product, quantity))
        return productRepository.save(
            product.copy(
                totalAvailableInStock = product.totalAvailableInStock - quantity,
            ),
        )
    }
}