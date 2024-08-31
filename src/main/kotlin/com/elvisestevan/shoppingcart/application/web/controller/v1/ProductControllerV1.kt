package com.elvisestevan.shoppingcart.application.web.controller.v1

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.v1.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.v1.ProductServiceV1
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
class ProductControllerV1(
    private val productServiceV1: ProductServiceV1,
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductControllerV1::class.java)
    }

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productServiceV1.findAll().map { ProductResponse.fromDomain(it) }
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: String,
    ): ProductResponse = productServiceV1.findById(productId).let { ProductResponse.fromDomain(it) }

    @PostMapping("/{productId}/reservations")
    fun makeReservation(
        @PathVariable productId: String,
        @RequestBody request: ReservationRequest,
    ): ProductResponse {
        log.info("Starting reservation for $productId of ${request.quantity} items")
        return productServiceV1.makeReservation(productId, request.quantity).let {
            log.info("Reservation for $productId made successfully, ${it.totalAvailableInStock} remaining")
            ProductResponse.fromDomain(it)
        }
    }
}