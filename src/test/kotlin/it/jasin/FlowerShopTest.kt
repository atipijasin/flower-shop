package it.jasin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class FlowerShopTest {
    @ParameterizedTest
    @MethodSource("provideSingleBundles")
    fun `should correctly generate invoice for single bundle orders`(input: String, expected: String) {
        val shop = FlowerShop(Bundles())

        val invoice = shop.generateInvoiceFor(input)

        assertEquals(expected, invoice)
    }

    @ParameterizedTest
    @MethodSource("provideMultipleSingleTypeBundles")
    fun `should correctly generate invoice for an order which contains multiple same-number bundles`(input: String, expected: String) {
        val shop = FlowerShop(Bundles())

        val invoice = shop.generateInvoiceFor(input)

        assertEquals(expected, invoice)
    }

    companion object {
        @JvmStatic
        private fun provideSingleBundles(): List<Arguments> {
            return listOf(
                "5 R12" to "5 R12 $6.99: 1 x 5 $6.99",
                "10 R12" to "10 R12 $12.99: 1 x 10 $12.99",
                "3 L09" to "3 L09 $9.95: 1 x 3 $9.95",
                "6 L09" to "6 L09 $16.95: 1 x 6 $16.95",
                "9 L09" to "9 L09 $24.95: 1 x 9 $24.95",
                "3 T58" to "3 T58 $5.95: 1 x 3 $5.95",
                "5 T58" to "5 T58 $9.95: 1 x 5 $9.95",
                "9 T58" to "9 T58 $16.99: 1 x 9 $16.99"
            )
                .map { Arguments.of(it.first, it.second) }
        }

        @JvmStatic
        private fun provideMultipleSingleTypeBundles(): List<Arguments> {
            return listOf(
                "50 R12" to "50 R12 $64.95: 5 x 10 $12.99",
                "15 L09" to "15 L09 $49.75: 5 x 3 $9.95",
                "6 T58" to "6 T58 $11.90: 2 x 3 $5.95",
            )
                .map { Arguments.of(it.first, it.second) }
        }
    }
}