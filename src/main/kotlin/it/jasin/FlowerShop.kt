package it.jasin

import java.math.BigDecimal

class FlowerShop(private val bundler: Bundler, private val accountant: Accountant) {
    fun generateInvoiceFor(order: String): String {
        val bundles = bundler.getBundlesFor(order)

        return accountant.calculatePriceBreakdownOf(bundles)
    }
}