package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v3

import com.elvisestevan.shoppingcart.resources.database.entity.v3.ProductV3
import io.micrometer.observation.annotation.Observed
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductJPARepositoryV3 : CrudRepository<ProductV3, String> {
    @Observed
    override fun findById(id: String): Optional<ProductV3>

    @Observed
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    fun save(productV3: ProductV3): ProductV3
}