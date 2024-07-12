package com.elvisestevan.shoppingcart

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest(classes = [ShoppingCartHighConcurrencyApplication::class])
class ShoppingCartHighConcurrencyApplicationTests {

	@Autowired
	lateinit var mockMvc: MockMvc

	val mapper = ObjectMapper().also {
		it.registerModule(JavaTimeModule())
		it.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		it.propertyNamingStrategy = PropertyNamingStrategies.LOWER_CAMEL_CASE
	}

}
