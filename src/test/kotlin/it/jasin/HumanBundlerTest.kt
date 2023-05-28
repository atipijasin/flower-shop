package it.jasin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class HumanBundlerTest {
    @Test
    fun `should correctly process single line order with one bundle`() {
        val result = HumanBundler().getBundlesFor("10 R12")

        assertThat(result).containsExactly(Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")))
    }

    @Test
    fun `should correctly process single line order with more than one same-type bundle`() {
        val result = HumanBundler().getBundlesFor("20 R12")

        assertThat(result).containsExactly(
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        )
    }

    @Test
    fun `should correctly process single order with different type of bundles for roses`() {
        assertThat(HumanBundler().getBundlesFor("15 R12")).containsExactlyInAnyOrder(
            Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        )
    }

    @Test
    fun `should correctly process single order with different type of bundles for lilies`() {
        assertThat(HumanBundler().getBundlesFor("15 L09")).containsExactlyInAnyOrder(
            Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
            Bundle(6, Flower("Lily", "L09"), BigDecimal("16.95"))
        )

    }
    @Test
    fun `should correctly process single order with different type of bundles for tulips`() {
        assertThat(HumanBundler().getBundlesFor("13 T58")).containsExactlyInAnyOrder(
            Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
            Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
            Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95"))
        )
    }

    @Test
    fun `should correctly handle multiple lines`() {
        assertThat(HumanBundler().getBundlesFor("10 R12\n9 L09")).containsExactlyInAnyOrder(
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
         )
    }
}
