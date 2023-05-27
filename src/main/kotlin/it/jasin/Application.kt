package it.jasin

import java.io.File

fun main() {
    val input = File("input.txt").readText()

    val bundler = HumanBundler()
    val accountant = HumanAccountant()
    val shop = FlowerShop(bundler, accountant)

    println(shop.generateInvoiceFor(input))
}