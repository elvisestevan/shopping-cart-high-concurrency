package com.elvisestevan.shoppingcart.application.web.controller

import com.elvisestevan.shoppingcart.ShoppingCartHighConcurrencyApplicationTests
import com.elvisestevan.shoppingcart.application.config.DataLoader
import com.elvisestevan.shoppingcart.application.web.dto.response.ProductResponse
import com.elvisestevan.shoppingcart.resources.database.entity.toDomain
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ProductControllerTest : ShoppingCartHighConcurrencyApplicationTests() {
    @Test
    fun `should get all products successfully`() {
        val response = DataLoader.data.map { ProductResponse.fromDomain(it.toDomain()) }

        mockMvc.perform(
            MockMvcRequestBuilders.get("/products"),
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(mapper.writeValueAsString(response)),
            )
    }

    @Test
    fun `should make a reservation successfully`() {
        val productId = "01J2M55YGRHWV1T72MK3PQXBYS"

        val response =
            DataLoader.data.first { it.id == productId }
                .copy(totalAvailableInStock = 9999999)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/products/$productId/reservations")
                .content("{ \"quantity\": 1 }")
                .contentType(MediaType.APPLICATION_JSON),
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(mapper.writeValueAsString(response)),
            )
    }
}
