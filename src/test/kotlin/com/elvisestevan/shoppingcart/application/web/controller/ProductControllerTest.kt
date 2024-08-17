package com.elvisestevan.shoppingcart.application.web.controller

import com.elvisestevan.shoppingcart.ShoppingCartHighConcurrencyApplicationTests
import com.elvisestevan.shoppingcart.application.config.DataLoader
import com.elvisestevan.shoppingcart.application.web.dto.response.ProductResponse
import com.elvisestevan.shoppingcart.resources.database.entity.toDomain
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ProductControllerTest : ShoppingCartHighConcurrencyApplicationTests() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductControllerTest::class.java)
    }

    @Autowired
    private lateinit var productController: ProductController

    @Test
    fun `should get all products successfully`() {
        val response = DataLoader.data.map { ProductResponse.fromDomain(it.toDomain()) }

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v2/products"),
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(mapper.writeValueAsString(response)),
            )
    }

    @Test
    fun `should make a reservation successfully`() {
        val productId = "01J2M5AYNSCZW28EGVZBGWAQSF"

        val response =
            DataLoader.data.first { it.id == productId }
                .copy(totalAvailableInStock = 999)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v2/products/$productId/reservations")
                .content("{ \"quantity\": 1 }")
                .contentType(MediaType.APPLICATION_JSON),
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(mapper.writeValueAsString(response)),
            )
    }

    @Test
    fun `should throw an exception when try to make a reservation of a quantity greater than available`() {
        val productId = "01J2M55YGRHWV1T72MK3PQXBYS"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v2/products/$productId/reservations")
                .content("{ \"quantity\": 10000000 }")
                .contentType(MediaType.APPLICATION_JSON),
        )
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
    }
}
