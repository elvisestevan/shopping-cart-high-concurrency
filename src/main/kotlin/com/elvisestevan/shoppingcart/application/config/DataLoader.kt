package com.elvisestevan.shoppingcart.application.config

import com.elvisestevan.shoppingcart.resources.database.entity.Product
import com.elvisestevan.shoppingcart.resources.database.repository.ProductJPARepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val productRepository: ProductJPARepository
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        // Load initial data into the database
        productRepository.save(Product("01J2M55YGRHWV1T72MK3PQXBYS", "Keyboard", "Keyboard with QWERTY layout and low-profile keys for a comfortable, quiet typing experience", 10000000, 10000000))
        productRepository.save(Product("01J2M5AYNSCZW28EGVZBGWAQSF", "Mouse", "Computer mouse for easily navigating a computer interface; click, scroll, and more", 10000000, 10000000))
        productRepository.save(Product("01J2M5B20RCGYRDMEHSG5NFY1H", "Monitor", "Full HD monitor with 1920x1080 resolution and AOC brand technology; ideal for webinars, meetings, digital work, entertainment, and more", 10000000, 10000000))
        productRepository.save(Product("01J2M5B4XR1XGSWRXB5BXTGMHA", "Headphone", "Collapsible Feature: Take your wired headphones wherever you go. Just fold them up, twist up the cord, and be on your merry way", 10000000, 10000000))
        productRepository.save(Product("01J2M5B8B8FPS2SFS5MHS6K0SP", "Printer", "The OfficeJet Pro 8135e is perfect for home offices printing professional-quality color documents like business documents, reports, presentations and flyers. Print speeds up to 10 ppm color, 20 ppm black", 10000000, 10000000))

    }
}