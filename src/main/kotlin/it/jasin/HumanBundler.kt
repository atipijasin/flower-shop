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
        var orderedFlowersAmount = order.split(" ")[0].toInt()
        val orderedFlowersCode = order.split(" ")[1]

        val availableBundlesForFlower = availableBundles
            .filter { it.flower.code == orderedFlowersCode }
            .sortedBy { it.flowersAmount }
            .reversed()

        val result = mutableListOf<Bundle>()

        while (orderedFlowersAmount > 0) {
            for (bundle in availableBundlesForFlower) {
                if((orderedFlowersAmount - bundle.flowersAmount) >= 0) {
                    result.add(bundle)
                    orderedFlowersAmount -= bundle.flowersAmount
                    break
                }
            }
        }


        return result
    }
}