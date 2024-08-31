package com.elvisestevan.shoppingcart.resources.database.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.Product
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v4.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4.ProductJPARepositoryV4
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
@Observed
class ProductRepositoryImplV4(
    private val productJPARepositoryV4: ProductJPARepositoryV4,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepositoryV4.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepositoryV4.findById(id).get().toDomain()

    override fun save(product: Product): Product = productJPARepositoryV4.save(com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductV4.fromDomain(product)).toDomain()
}
