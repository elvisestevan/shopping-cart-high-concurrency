package com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4

import com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductReservation
import org.springframework.data.repository.CrudRepository

interface ProductReservationJPARepository : CrudRepository<ProductReservation, String>