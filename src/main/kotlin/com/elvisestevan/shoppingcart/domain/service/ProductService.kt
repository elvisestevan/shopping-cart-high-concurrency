package com.elvisestevan.shoppingcart.domain.service

import com.elvisestevan.shoppingcart.domain.entity.Product
import com.elvisestevan.shoppingcart.domain.entity.ProductReservation
import com.elvisestevan.shoppingcart.domain.repository.ProductRepository
import com.elvisestevan.shoppingcart.domain.repository.ProductReservationRepository
import de.huxhorn.sulky.ulid.ULID
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productReservationRepository: ProductReservationRepository,
) {
    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(productId: String): Product = productRepository.findById(productId)

    fun makeReservation(
        productId: String,
        quantity: Int,
    ): Product {
        val product = productRepository.findById(productId)
        if (product.totalAvailableInStock < quantity) {
            throw Exception(
                "error on making reservation, total available is ${product.totalAvailableInStock} " +
                    "and you're trying to make a reservation of $quantity items",
            )
        }
        productReservationRepository.save(ProductReservation(ULID().nextULID(), product, quantity))
        return productRepository.save(
            product.copy(
                totalAvailableInStock = product.totalAvailableInStock - quantity,
            ),
        )
    }
}
