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

    @Test
    fun `should correctly calculate price breakdown of multiple same-type bundles of same flower`() {
        val result = HumanAccountant().calculatePriceBreakdownOf(listOf(
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        ))

        assertThat(result).isEqualTo("You have ordered 20 R12 for a total of $25.98: 2 bundle(s) of 10 R12 (bundle price: $12.99)")
    }

    @Test
    fun `should correctly calculate price breakdown of multiple different-type bundles of same flower`() {
        val result = HumanAccountant().calculatePriceBreakdownOf(listOf(
            Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        ))

        assertThat(result).contains("You have ordered 15 R12 for a total of $19.98")
        assertThat(result).contains("1 bundle(s) of 10 R12 (bundle price: $12.99)")
        assertThat(result).contains("1 bundle(s) of 5 R12 (bundle price: $6.99)")

    }

    @Test
    fun `should correctly calculate price breakdown of multiple different-type bundles for different flowers`() {
        val result = HumanAccountant().calculatePriceBreakdownOf(listOf(
            Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
            Bundle(3, Flower("Lily", "L09"), BigDecimal("9.95")),
            Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95"))
        ))


        assertThat(result).contains("You have ordered 15 R12 for a total of $19.98")
        assertThat(result).contains("1 bundle(s) of 10 R12 (bundle price: $12.99)")
        assertThat(result).contains("1 bundle(s) of 5 R12 (bundle price: $6.99)")

        assertThat(result).contains("You have ordered 12 L09 for a total of $34.90")
        assertThat(result).contains("1 bundle(s) of 9 L09 (bundle price: $24.95)")
        assertThat(result).contains("1 bundle(s) of 3 L09 (bundle price: $9.95)")

        assertThat(result).contains("You have ordered 3 T58 for a total of $5.95")
        assertThat(result).contains("1 bundle(s) of 3 T58 (bundle price: $5.95)")

    }
}