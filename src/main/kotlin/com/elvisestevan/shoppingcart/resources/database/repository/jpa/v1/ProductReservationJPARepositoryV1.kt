package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v1

import com.elvisestevan.shoppingcart.resources.database.entity.v1.ProductReservationV1
import org.springframework.data.repository.CrudRepository

interface ProductReservationJPARepositoryV1 : CrudRepository<ProductReservationV1, String>