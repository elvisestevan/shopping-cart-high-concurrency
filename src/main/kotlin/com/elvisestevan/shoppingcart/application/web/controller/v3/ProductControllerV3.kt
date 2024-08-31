package com.elvisestevan.shoppingcart.application.web.controller.v3

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.v3.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.v3.ProductServiceV3
import io.micrometer.observation.annotation.Observed
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v3/products")
@Observed
class ProductControllerV3(
    private val productServiceV3: ProductServiceV3,
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductControllerV3::class.java)
    }

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productServiceV3.findAll().map { ProductResponse.fromDomain(it) }
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: String,
    ): ProductResponse = productServiceV3.findById(productId).let { ProductResponse.fromDomain(it) }

    @PostMapping("/{productId}/reservations")
    fun makeReservation(
        @PathVariable productId: String,
        @RequestBody request: ReservationRequest,
    ): ProductResponse {
        log.info("Starting reservation for $productId of ${request.quantity} items")
        return productServiceV3.makeReservation(productId, request.quantity).let {
            log.info("Reservation for $productId made successfully")
            ProductResponse.fromDomain(it)
        }
    }
}