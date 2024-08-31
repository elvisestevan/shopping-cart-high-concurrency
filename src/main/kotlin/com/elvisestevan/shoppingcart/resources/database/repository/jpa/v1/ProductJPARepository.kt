package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1

import com.elvisestevan.shoppingcart.resources.database.entity.v1.Product
import org.springframework.data.repository.CrudRepository

interface ProductJPARepository : CrudRepository<Product, String>