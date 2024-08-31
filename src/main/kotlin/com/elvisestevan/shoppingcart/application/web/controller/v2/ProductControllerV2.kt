package com.elvisestevan.shoppingcart.application.web.controller.v2

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.v1.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.v2.ProductServiceV2
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/products")
class ProductControllerV2(
    private val productServiceV2: ProductServiceV2,
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductControllerV2::class.java)
    }

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productServiceV2.findAll().map { ProductResponse.fromDomain(it) }
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: String,
    ): ProductResponse = productServiceV2.findById(productId).let { ProductResponse.fromDomain(it) }

    @PostMapping("/{productId}/reservations")
    fun makeReservation(
        @PathVariable productId: String,
        @RequestBody request: ReservationRequest,
    ): ProductResponse {
        log.info("Starting reservation for $productId of ${request.quantity} items")
        return productServiceV2.makeReservation(productId, request.quantity).let {
            log.info("Reservation for $productId made successfully, ${it.totalAvailableInStock} remaining")
            ProductResponse.fromDomain(it)
        }
    }
}