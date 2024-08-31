package com.elvisestevan.shoppingcart.resources.database.repository.v3

import com.elvisestevan.shoppingcart.domain.entity.v3.Product
import com.elvisestevan.shoppingcart.domain.repository.v3.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v3.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v3.ProductJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository
import com.elvisestevan.shoppingcart.resources.database.entity.v3.Product as ProductEntity

@Repository
class ProductRepositoryImpl(
    private val productJPARepository: ProductJPARepository,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepository.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepository.findById(id).get().toDomain()

    @Observed
    override fun save(product: Product): Product = productJPARepository.save(ProductEntity.fromDomain(product)).toDomain()
}