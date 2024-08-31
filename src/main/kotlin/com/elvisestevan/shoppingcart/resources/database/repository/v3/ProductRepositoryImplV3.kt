package com.elvisestevan.shoppingcart.resources.database.repository.v3

import com.elvisestevan.shoppingcart.domain.entity.v3.Product
import com.elvisestevan.shoppingcart.domain.repository.v3.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v3.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v3.ProductJPARepositoryV3
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository
import com.elvisestevan.shoppingcart.resources.database.entity.v3.ProductV3 as ProductEntity

@Repository
class ProductRepositoryImplV3(
    private val productJPARepositoryV3: ProductJPARepositoryV3,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepositoryV3.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepositoryV3.findById(id).get().toDomain()

    @Observed
    override fun save(product: Product): Product = productJPARepositoryV3.save(ProductEntity.fromDomain(product)).toDomain()
}