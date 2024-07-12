package com.elvisestevan.shoppingcart.resources.database.repository

import com.elvisestevan.shoppingcart.resources.database.entity.Product
import org.springframework.data.repository.CrudRepository

interface ProductJPARepository : CrudRepository<Product, String>