package com.elvisestevan.shoppingcart.domain.service

import com.elvisestevan.shoppingcart.domain.entity.Product
import com.elvisestevan.shoppingcart.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(productId: String): Product = productRepository.findById(productId)

    fun makeReservation(
        productId: String,
        quantity: Int,
    ): Product {
        val product = productRepository.findById(productId)
        return productRepository.save(
            product.copy(
                totalAvailableInStock = product.totalAvailableInStock - quantity,
            ),
        )
    }
}
