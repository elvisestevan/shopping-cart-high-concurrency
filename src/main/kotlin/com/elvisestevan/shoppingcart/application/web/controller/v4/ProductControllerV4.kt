package com.elvisestevan.shoppingcart.application.web.controller.v4

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.ProductReservationResponse
import com.elvisestevan.shoppingcart.application.web.dto.response.v4.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.v4.ProductServiceV4
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
@RequestMapping("/api/v4/products")
@Observed
class ProductControllerV4(
    private val productServiceV4: ProductServiceV4,
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductControllerV4::class.java)
    }

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productServiceV4.findAll().map { ProductResponse.fromDomain(it) }
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: String,
    ): ProductResponse = productServiceV4.findById(productId).let { ProductResponse.fromDomain(it) }

    @PostMapping("/{productId}/reservations")
    fun makeReservation(
        @PathVariable productId: String,
        @RequestBody request: ReservationRequest,
    ): ProductReservationResponse {
        log.info("Starting reservation for $productId of ${request.quantity} items")
        return productServiceV4.makeReservation(productId, request.quantity).let {
            log.info("Reservation for $productId made successfully")
            ProductReservationResponse.fromDomain(it)
        }
    }
}
