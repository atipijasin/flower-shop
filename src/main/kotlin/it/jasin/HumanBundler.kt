package it.jasin

class HumanBundler(private val bundleRepository: BundleRepository) : Bundler {
    override fun getBundlesFor(order: String): List<Bundle> {
        val singleOrders = order.split("\n")

        return singleOrders.flatMap { singleOrder ->
            val resultForOrder = mutableListOf<Bundle>()
            val (flowerAmount, flowerCode) = extractSingleOrderInformation(singleOrder)
            val availableBundlesForFlower = bundleRepository.availableBundlesSortedByFlowersAmountFor(flowerCode).toMutableList()

            var currentRemainingFlowers = flowerAmount
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
                        currentRemainingFlowers = flowerAmount
                        resultForOrder.clear()
                        clear = true
                    }
                }
            }
            resultForOrder
        }

    }

    private fun extractSingleOrderInformation(singleOrder: String): Pair<Int, String> {
        return singleOrder.split(" ")[0].toInt() to singleOrder.split(" ")[1]
    }
}