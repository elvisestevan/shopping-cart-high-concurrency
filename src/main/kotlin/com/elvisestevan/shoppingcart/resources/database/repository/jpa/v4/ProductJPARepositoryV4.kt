package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4

import com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductV4
import io.micrometer.observation.annotation.Observed
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ProductJPARepositoryV4 : CrudRepository<ProductV4, String> {
    @Observed
    override fun findById(id: String): Optional<ProductV4>

    @Observed
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    fun save(productV4: ProductV4): ProductV4
}
