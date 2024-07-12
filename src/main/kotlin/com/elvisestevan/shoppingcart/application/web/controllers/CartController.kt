package com.elvisestevan.shoppingcart.application.web.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController {

    @GetMapping("/carts")
    fun addProduct(): String {
        return "hello"
    }

}