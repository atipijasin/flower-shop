package it.jasin

class HumanAccountant: Accountant {
    override fun calculatePriceBreakdownOf(bundles: List<Bundle>): String {

        return bundles
            .groupBy { it.flower.code }
            .map { flowerCodeToBundles ->
                val totalPrice = flowerCodeToBundles.value.sumOf { it.usdPrice }
                val totalFlowers = flowerCodeToBundles.value.sumOf { it.flowersAmount }

                val post = flowerCodeToBundles.value
                    .groupBy { it.flowersAmount }
                    .map {
                        "${it.value.size} bundle(s) of ${it.key} ${flowerCodeToBundles.key} (bundle price: \$${it.value.first().usdPrice})"
                    }.joinToString(", ")

                "You have ordered $totalFlowers ${flowerCodeToBundles.key} for a total of \$$totalPrice: " + post
            }
            .joinToString("\n")
    }
}