package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v2

import com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductV1
import io.micrometer.observation.annotation.Observed
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ProductJPARepositoryV2 : CrudRepository<ProductV1, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Observed
    override fun findById(id: String): Optional<ProductV1>
}