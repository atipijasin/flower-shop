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
        val result = mutableListOf<Bundle>()

        val orders = order.split("\n")

        orders.forEach {
            val resultForOrder = mutableListOf<Bundle>()
            val orderedFlowersAmount = it.split(" ")[0].toInt()
            val orderedFlowersCode = it.split(" ")[1]

            val availableBundlesForFlower = availableBundles
                .filter { it.flower.code == orderedFlowersCode }
                .sortedBy { it.flowersAmount }
                .reversed()
                .toMutableList()


            var currentRemainingFlowers = orderedFlowersAmount
            var clear = false

            while (currentRemainingFlowers > 0 && availableBundlesForFlower.isNotEmpty()) {
                if (clear) { availableBundlesForFlower.removeAt(0) }
                for ((index,bundle) in availableBundlesForFlower.withIndex()) {
                    if((currentRemainingFlowers - bundle.flowersAmount) >= 0) {
                        resultForOrder.add(bundle)
                        currentRemainingFlowers -= bundle.flowersAmount
                        clear = false
                        break
                    } else if (index == availableBundlesForFlower.lastIndex) {
                        currentRemainingFlowers = orderedFlowersAmount
                        resultForOrder.clear()
                        clear = true
                    }
                }
            }
            result.addAll(resultForOrder)
            resultForOrder.clear()
        }

        return result
    }
}