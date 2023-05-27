package it.jasin

class HumanAccountant: Accountant {
    override fun calculatePriceBreakdownOf(bundles: List<Bundle>): String {
        val totalPrice = bundles.sumOf { it.usdPrice }
        val totalFlowers = bundles.sumOf { it.flowersAmount }
        val bundleFlowersAmount = bundles.first().flowersAmount
        val flowerCode = bundles.first().flower.code
        val singleBundlePrice = bundles.first().usdPrice

        return "You have ordered $totalFlowers $flowerCode for a total of \$$totalPrice: ${bundles.size} bundle(s) of $bundleFlowersAmount $flowerCode (bundle price: \$$singleBundlePrice)"
    }
}