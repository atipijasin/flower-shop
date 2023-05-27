package it.jasin

import java.math.BigDecimal

class FlowerShop(private val bundles: Bundles) {
    fun generateInvoiceFor(order: String): String {
        val orderedFlowersAmount = order.split(" ")[0].toInt()
        val orderedFlowersCode = order.split(" ")[1]

        val bundle = bundles
            .available()
            .filter { it.flower.code == orderedFlowersCode }
            .filter { orderedFlowersAmount % it.amount == 0 }
            .maxByOrNull { it.amount }
            ?: return "Invalid order"

        return "$order \$${bundle.usdPrice * BigDecimal(orderedFlowersAmount/bundle.amount)}: ${orderedFlowersAmount/bundle.amount} x ${bundle.amount} \$${bundle.usdPrice}"
    }
}