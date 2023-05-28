package it.jasin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class ApplicationKtTest() {
    private val input = File("input.txt").readText()
    @Test
    fun `should correctly output complete test`() {
        val bundler = HumanBundler()
        val accountant = HumanAccountant()

        val result = FlowerShop(bundler, accountant).generateInvoiceFor(input)

        assertThat(result).isEqualTo(
            """
                You have ordered 10 R12 for a total of ${'$'}12.99: 1 bundle(s) of 10 R12 (bundle price: ${'$'}12.99)
                You have ordered 15 L09 for a total of ${'$'}41.90: 1 bundle(s) of 9 L09 (bundle price: ${'$'}24.95), 1 bundle(s) of 6 L09 (bundle price: ${'$'}16.95)
                You have ordered 13 T58 for a total of ${'$'}25.85: 2 bundle(s) of 5 T58 (bundle price: ${'$'}9.95), 1 bundle(s) of 3 T58 (bundle price: ${'$'}5.95)
            """.trimIndent()
        )
    }
}