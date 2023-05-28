package it.jasin

interface BundleRepository {
    fun availableBundlesSortedByFlowersAmountFor(flowerCode: String): List<Bundle>
}