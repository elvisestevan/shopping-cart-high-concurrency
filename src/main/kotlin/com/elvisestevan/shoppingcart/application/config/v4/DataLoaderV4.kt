package com.elvisestevan.shoppingcart.application.config.v4

import com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductV4
import com.elvisestevan.shoppingcart.resources.database.entity.v4.ProductStock
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.v4.ProductJPARepositoryV4
import com.elvisestevan.shoppingcart.resources.database.repository.jpa.ProductStockJPARepository
import de.huxhorn.sulky.ulid.ULID
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoaderV4(
    private val productRepository: ProductJPARepositoryV4,
    private val productStockJPARepository: ProductStockJPARepository,
) : CommandLineRunner {
    companion object {
        val data =
            listOf(
                ProductV4(
                    id = "01J2M55YGRHWV1T72MK3PQXBYS",
                    name = "Keyboard",
                    description = "Keyboard with QWERTY layout and low-profile keys for a comfortable, quiet typing experience",
                    version = 1,
                ),
                ProductV4(
                    id = "01J2M5AYNSCZW28EGVZBGWAQSF",
                    name = "Mouse",
                    description = "Computer mouse for easily navigating a computer interface; click, scroll, and more",
                    version = 1,
                ),
                ProductV4(
                    id = "01J2M5B20RCGYRDMEHSG5NFY1H",
                    name = "Monitor",
                    description =
                        "Full HD monitor with 1920x1080 resolution and AOC brand technology; ideal for webinars, " +
                            "meetings, digital work, entertainment, and more",
                    version = 1,
                ),
                ProductV4(
                    id = "01J2M5B4XR1XGSWRXB5BXTGMHA",
                    name = "Headphone",
                    description =
                        "Collapsible Feature: Take your wired headphones wherever you go. Just fold them up, " +
                            "twist up the cord, and be on your merry way",
                    version = 1,
                ),
                ProductV4(
                    id = "01J2M5B8B8FPS2SFS5MHS6K0SP",
                    name = "Printer",
                    description =
                        "The OfficeJet Pro 8135e is perfect for home offices printing professional-quality color " +
                            "documents like business documents, reports, presentations and flyers. Print speeds up to 10 ppm color, " +
                            "20 ppm black",
                    version = 1,
                ),
            )
    }

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        productRepository.saveAll(data)

        data.forEach { product ->
            val stock =
                (1..6000).map {
                    ProductStock(
                        id = ULID().nextULID(),
                        productV4 = product,
                    )
                }
            productStockJPARepository.saveAll(stock)
        }
    }
}
