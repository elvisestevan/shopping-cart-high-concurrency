package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v3

import com.elvisestevan.shoppingcart.resources.database.entity.v3.Product
import io.micrometer.observation.annotation.Observed
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductJPARepository : CrudRepository<Product, String> {
    @Observed
    override fun findById(id: String): Optional<Product>

    @Observed
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    fun save(product: Product): Product
}