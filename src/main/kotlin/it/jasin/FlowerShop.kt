package it.jasin

import java.math.BigDecimal

class FlowerShop(private val bundles: Bundles) {
    fun generateInvoiceFor(order: String): String {
        val orderedFlowersAmount = order.split(" ")[0]
        val orderedFlowersCode = order.split(" ")[1]

        val bundle = bundles
            .available()
            .first { it.flower.code == orderedFlowersCode && it.amount == orderedFlowersAmount.toInt() }

        return "$order \$${bundle.usdPrice}: 1 x ${bundle.amount} \$${bundle.usdPrice}"
    }
}