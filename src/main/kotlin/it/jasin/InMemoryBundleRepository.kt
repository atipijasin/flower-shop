package it.jasin

import java.math.BigDecimal

class InMemoryBundleRepository : BundleRepository {
    private val bundles = listOf(
        Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
        Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
        Bundle(3, Flower("Lily", "L09"), BigDecimal("9.95")),
        Bundle(6, Flower("Lily", "L09"), BigDecimal("16.95")),
        Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
        Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95")),
        Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
        Bundle(9, Flower("Tulip", "T58"), BigDecimal("16.99"))
    )

    override fun availableBundlesSortedByFlowersAmount(flowerCode: String): List<Bundle> {
        return bundles
            .filter { it.flower.code == flowerCode }
            .sortedBy { it.flowersAmount }
            .reversed()
            .toMutableList()
    }
}