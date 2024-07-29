package com.elvisestevan.shoppingcart.application.web.controller

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService,
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductController::class.java)
    }

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productService.findAll().map { ProductResponse.fromDomain(it) }
    }

    @PostMapping("/{productId}/reservations")
    fun makeReservation(
        @PathVariable productId: String,
        @RequestBody request: ReservationRequest,
    ): ProductResponse {
        log.info("Starting reservation for $productId of ${request.quantity} items")
        return productService.makeReservation(productId, request.quantity).let { ProductResponse.fromDomain(it) }
    }
}
