package com.elvisestevan.shoppingcart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShoppingCartHighConcurrencyApplication

fun main(args: Array<String>) {
    runApplication<ShoppingCartHighConcurrencyApplication>(*args)
}
