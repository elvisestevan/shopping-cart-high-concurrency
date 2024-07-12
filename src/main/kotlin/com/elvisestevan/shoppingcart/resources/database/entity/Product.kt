package com.elvisestevan.shoppingcart.resources.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class Product(

    @Id
    @Column
    val id: String,

    @Column
    val name: String,

    @Column
    val description: String,

    @Column
    val totalAvailableInStock: Int,

    @Column
    val totalInStock: Int,

)