package it.jasin

interface BundleRepository {
    fun availableBundlesSortedByFlowersAmount(flowerCode: String): List<Bundle>
}