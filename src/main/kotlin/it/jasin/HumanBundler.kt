package it.jasin

import java.math.BigDecimal

class HumanBundler : Bundler {

    private val availableBundles = listOf(
        Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
        Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
        Bundle(3, Flower("Lily", "L09"), BigDecimal("9.95")),
        Bundle(6, Flower("Lily", "L09"), BigDecimal("16.95")),
        Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
        Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95")),
        Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
        Bundle(9, Flower("Tulip", "T58"), BigDecimal("16.99"))
    )

    override fun getBundlesFor(order: String): List<Bundle> {
        val orderedFlowersAmount = order.split(" ")[0].toInt()
        val orderedFlowersCode = order.split(" ")[1]

        val bundle = availableBundles
            .filter { it.flower.code == orderedFlowersCode }
            .filter { orderedFlowersAmount % it.flowersAmount == 0 }
            .maxByOrNull { it.flowersAmount }!!

        return List(orderedFlowersAmount/bundle.flowersAmount) { bundle }
    }
}