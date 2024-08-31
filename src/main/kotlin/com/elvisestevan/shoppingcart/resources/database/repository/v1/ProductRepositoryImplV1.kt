package com.elvisestevan.shoppingcart.resources.database.repository.v1

import com.elvisestevan.shoppingcart.domain.entity.v1.Product
import com.elvisestevan.shoppingcart.domain.repository.v1.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1.ProductJPARepositoryV1
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository
import com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductV1 as ProductEntity

@Repository
class ProductRepositoryImplV1(
    private val productJPARepositoryV1: ProductJPARepositoryV1,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepositoryV1.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepositoryV1.findById(id).get().toDomain()

    @Observed
    override fun save(product: Product): Product = productJPARepositoryV1.save(ProductEntity.fromDomain(product)).toDomain()
}