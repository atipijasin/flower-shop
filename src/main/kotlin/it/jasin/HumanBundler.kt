package it.jasin

class HumanBundler(private val bundleRepository: BundleRepository) : Bundler {
    override fun getBundlesFor(order: String): List<Bundle> {
        return order.split("\n")
            .map(::extractSingleOrderInformation)
            .map { (flowerAmount, flowerCode) -> wrapBundles(flowerAmount, flowerCode) }
            .flatten()
    }

    private fun extractSingleOrderInformation(singleOrder: String): Pair<Int, String> {
        return singleOrder.split(" ")[0].toInt() to singleOrder.split(" ")[1]
    }

    private fun wrapBundles(flowerAmount: Int, flowerCode: String): List<Bundle> {
        val wrappedBundles = mutableListOf<Bundle>()
        val availableBundlesForFlower =
            bundleRepository.availableBundlesSortedByFlowersAmount(flowerCode).toMutableList()

        var currentRemainingFlowers = flowerAmount

        while (thereAreFlowersRemaining(currentRemainingFlowers, availableBundlesForFlower)) {
            for ((index, bundle) in availableBundlesForFlower.withIndex()) {
                if (bundleIsValid(currentRemainingFlowers, bundle)) {
                    wrappedBundles.add(bundle)
                    currentRemainingFlowers -= bundle.flowersAmount
                    break
                } else if (noMoreBundlesAvailable(index, availableBundlesForFlower)) {
                    // backtracking phase
                    while (wrappedBundles.last() == bundle) {
                        currentRemainingFlowers += wrappedBundles.last().flowersAmount
                        wrappedBundles.removeLast()
                    }
                    val nextSmallerBundleIndex = availableBundlesForFlower.indexOf (wrappedBundles.last()) + 1

                    currentRemainingFlowers += wrappedBundles.last().flowersAmount
                    wrappedBundles.removeLast()

                    currentRemainingFlowers -= availableBundlesForFlower[nextSmallerBundleIndex].flowersAmount
                    wrappedBundles.add(availableBundlesForFlower[nextSmallerBundleIndex])
                }
            }
        }
        return wrappedBundles
    }

    private fun thereAreFlowersRemaining(
        currentRemainingFlowers: Int,
        availableBundlesForFlower: MutableList<Bundle>
    ) = currentRemainingFlowers > 0 && availableBundlesForFlower.isNotEmpty()

    private fun bundleIsValid(currentRemainingFlowers: Int, bundle: Bundle) =
        (currentRemainingFlowers - bundle.flowersAmount) >= 0

    private fun noMoreBundlesAvailable(
        index: Int,
        availableBundlesForFlower: MutableList<Bundle>
    ) = index == availableBundlesForFlower.lastIndex

}