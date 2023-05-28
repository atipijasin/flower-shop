package it.jasin

import java.io.File

fun main() {
    val input = File("input.txt").readText()
    val inMemoryBundleRepository = InMemoryBundleRepository()
    val bundler = HumanBundler(inMemoryBundleRepository)
    val accountant = HumanAccountant()
    val shop = FlowerShop(bundler, accountant)

    println("=========================FLOWER SHOP RESULT==================================")
    println(shop.generateInvoiceFor(input))
    println("=============================================================================")
}