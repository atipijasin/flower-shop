package it.jasin

interface Bundler {
    fun getBundlesFor(order: String): List<Bundle>
}