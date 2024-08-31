package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1

import com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductV1
import org.springframework.data.repository.CrudRepository

interface ProductJPARepositoryV1 : CrudRepository<ProductV1, String>