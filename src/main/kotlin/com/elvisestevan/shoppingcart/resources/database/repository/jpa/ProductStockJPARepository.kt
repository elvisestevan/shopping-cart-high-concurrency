package com.elvisestevan.shoppingcart.resources.database.repository.jpa

import com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ProductStockJPARepository : JpaRepository<ProductStock, String> {
    @Modifying
    @Query(
        nativeQuery = true,
        value = """
            UPDATE product_stock SET product_reservation_id = ?1, updated_at = now() WHERE id in
            (SELECT id FROM product_stock WHERE 
            product_id = ?2 AND product_reservation_id is null
            LIMIT ?3
            FOR UPDATE
            SKIP LOCKED)
        """,
    )
    fun makeReservationInStock(
        productReservationId: String,
        productId: String,
        quantity: Int,
    ): Int
}
