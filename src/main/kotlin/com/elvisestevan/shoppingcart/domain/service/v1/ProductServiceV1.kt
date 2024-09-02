package com.elvisestevan.shoppingcart.domain.service.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.Product
import com.elvisestevan.shoppingcart.domain.entity.v1.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductRepository
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductReservationRepository
import de.huxhorn.sulky.ulid.ULID
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductServiceV1(
    private val productRepositoryImplV1: ProductRepository,
    private val productReservationRepositoryImplV1: ProductReservationRepository,
) {
    fun findAll(): List<Product> = productRepositoryImplV1.findAll()

    fun findById(productId: String): Product = productRepositoryImplV1.findById(productId)

    fun makeReservation(
        productId: String,
        quantity: Int,
    ): Product {
        val product = productRepositoryImplV1.findById(productId)
        if (product.totalAvailableInStock < quantity) {
            throw ResponseStatusException(
                HttpStatusCode.valueOf(500),
                "error on making reservation, total available is ${product.totalAvailableInStock} " +
                        "and you're trying to make a reservation of $quantity items",
            )
        }
        productReservationRepositoryImplV1.save(ProductReservation(ULID().nextULID(), product, quantity))
        return productRepositoryImplV1.save(
            product.copy(
                totalAvailableInStock = product.totalAvailableInStock - quantity,
            ),
        )
    }
}