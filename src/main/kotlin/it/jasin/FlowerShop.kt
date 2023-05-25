package it.jasin

class FlowerShop {
    private val products = mapOf(
        "R12" to mapOf(
            "5" to "$6.99",
            "10" to "$12.99"
        ),
        "L09" to mapOf(
            "3" to "$9.95",
            "6" to "$16.95",
            "9" to "$24.95"
        ),
        "T58" to mapOf(
            "3" to "$5.95",
            "5" to "$9.95",
            "9" to "$16.99"
        )
    )
    fun generateInvoiceFor(order: String): String {
        return order
            .split(" ")
            .let { "$order ${products[it[1]]?.get(it[0])}: 1 x ${it[0]} ${products[it[1]]?.get(it[0])}" }
    }
}