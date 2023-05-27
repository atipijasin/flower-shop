package it.jasin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
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
    fun `should correctly process single order with different type of bundles`() {
        val result = HumanBundler().getBundlesFor("15 R12")

        assertThat(result).containsExactlyInAnyOrder(
            Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        )
    }
}
