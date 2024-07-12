package com.elvisestevan.shoppingcart.resources.database.repository

import com.elvisestevan.shoppingcart.domain.entity.Product
import com.elvisestevan.shoppingcart.domain.repository.ProductRepository
import com.elvisestevan.shoppingcart.resources.database.entity.Product as ProductEntity
import com.elvisestevan.shoppingcart.resources.database.entity.toDomain
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.ProductJPARepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ProductRepositoryImpl(
    private val productJPARepository: ProductJPARepository
) : ProductRepository {
    override fun findAll(): List<Product> =
        productJPARepository.findAll().map { it.toDomain() }

    override fun findById(id: String): Product =
        productJPARepository.findById(id).get().toDomain()

    override fun save(product: Product): Product =
        productJPARepository.save(ProductEntity.fromDomain(product)).toDomain()

}