package com.elvisestevan.shoppingcart.resources.database.repository.v4

import com.elvisestevan.shoppingcart.domain.entity.v4.Product
import com.elvisestevan.shoppingcart.domain.repository.v4.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.v4.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4.ProductJPARepository
import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Repository

@Repository
@Observed
class ProductRepositoryImpl(
    private val productJPARepository: ProductJPARepository,
) : ProductRepository {
    override fun findAll(): List<Product> = productJPARepository.findAll().map { it.toDomain() }

    override fun findById(id: String): Product = productJPARepository.findById(id).get().toDomain()

    override fun save(product: Product): Product = productJPARepository.save(com.elvisestevan.shoppingcart.resources.database.entity.v4.Product.fromDomain(product)).toDomain()
}
