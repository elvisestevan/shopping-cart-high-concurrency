package com.elvisestevan.shoppingcart.resources.database.repository.v2

import com.elvisestevan.shoppingcart.domain.entity.v1.Product
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v2.ProductJPARepositoryV2
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductV1 as ProductEntity

@Repository
class ProductRepositoryImplV2(
    private val productJPARepositoryV2: ProductJPARepositoryV2,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepositoryV2.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepositoryV2.findById(id).get().toDomain()

    @Observed
    override fun save(product: Product): Product = productJPARepositoryV2.save(ProductEntity.fromDomain(product)).toDomain()
}