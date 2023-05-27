package it.jasin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class HumanAccountantTest {
    @Test
    fun `should correctly calculate price breakdown of single bundle`() {
        val result = HumanAccountant().calculatePriceBreakdownOf(listOf(Bundle(5, Flower("Rose", "R12"), BigDecimal("5.99"))))

        assertThat(result).isEqualTo("You have ordered 5 R12 for a total of $5.99: 1 bundle(s) of 5 R12 (bundle price: $5.99)")
    }
}