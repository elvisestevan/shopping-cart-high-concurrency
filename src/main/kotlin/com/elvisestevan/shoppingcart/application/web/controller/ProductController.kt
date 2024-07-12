package com.elvisestevan.shoppingcart.application.web.controller

import com.elvisestevan.shoppingcart.application.web.dto.request.ReservationRequest
import com.elvisestevan.shoppingcart.application.web.dto.response.ProductResponse
import com.elvisestevan.shoppingcart.domain.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController (
    private val productService: ProductService
) {

    @GetMapping("/products")
    fun getProducts(): List<ProductResponse> {
        return productService.findAll().map { ProductResponse.fromDomain(it) };
    }

    @PostMapping("/products/{productId}/reservations")
    fun makeReservation(@PathVariable productId: String, @RequestBody request: ReservationRequest) {
        productService.makeReservation(productId, request.quantity)
    }

}