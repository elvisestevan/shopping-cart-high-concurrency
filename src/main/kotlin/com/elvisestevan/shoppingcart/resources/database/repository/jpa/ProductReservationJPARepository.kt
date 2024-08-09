package com.elvisestevan.shoppingcart.resources.database.repository.jpa

import com.elvisestevan.shoppingcart.resources.database.entity.ProductReservation
import org.springframework.data.repository.CrudRepository

interface ProductReservationJPARepository : CrudRepository<ProductReservation, String>
