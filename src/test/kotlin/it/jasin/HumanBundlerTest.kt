package it.jasin

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class HumanBundlerTest {
    private val bundleRepository = mockk<BundleRepository>()
    private val bundler = HumanBundler(bundleRepository)

    @BeforeEach
    fun setUp() {
        every { bundleRepository.availableBundlesSortedByFlowersAmount("R12") } returns listOf(
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99"))

        )

        every { bundleRepository.availableBundlesSortedByFlowersAmount("L09") } returns listOf(
            Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
            Bundle(6, Flower("Lily", "L09"), BigDecimal("16.95")),
            Bundle(3, Flower("Lily", "L09"), BigDecimal("9.95"))
        )

        every { bundleRepository.availableBundlesSortedByFlowersAmount("T58") } returns listOf(
            Bundle(9, Flower("Tulip", "T58"), BigDecimal("16.99")),
            Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
            Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95"))
        )
    }

    @Test
    fun `should correctly process single line order with one bundle`() {
        val result = bundler.getBundlesFor("10 R12")

        assertThat(result).containsExactly(Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")))
    }

    @Test
    fun `should correctly process single line order with more than one same-type bundle`() {
        val result = bundler.getBundlesFor("20 R12")

        assertThat(result).containsExactly(
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
            Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
        )
    }

    @Test
    fun `should correctly process single order with different type of bundles for roses`() {
        assertThat(bundler.getBundlesFor("15 R12"))
            .containsExactlyInAnyOrder(
                Bundle(5, Flower("Rose", "R12"), BigDecimal("6.99")),
                Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99"))
            )
    }

    @Test
    fun `should correctly process single order with different type of bundles for lilies`() {
        assertThat(bundler.getBundlesFor("15 L09"))
            .containsExactlyInAnyOrder(
                Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
                Bundle(6, Flower("Lily", "L09"), BigDecimal("16.95"))
            )

    }

    @Test
    fun `should correctly process single order with different type of bundles for tulips`() {
        assertThat(bundler.getBundlesFor("13 T58"))
            .containsExactlyInAnyOrder(
                Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
                Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
                Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95"))
            )

        assertThat(bundler.getBundlesFor("22 T58"))
            .containsExactlyInAnyOrder(
                Bundle(9, Flower("Tulip", "T58"), BigDecimal("16.99")),
                Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
                Bundle(5, Flower("Tulip", "T58"), BigDecimal("9.95")),
                Bundle(3, Flower("Tulip", "T58"), BigDecimal("5.95"))
            )

    }

    @Test
    fun `should correctly handle multiple lines`() {
        assertThat(bundler.getBundlesFor("10 R12\n9 L09"))
            .containsExactlyInAnyOrder(
                Bundle(10, Flower("Rose", "R12"), BigDecimal("12.99")),
                Bundle(9, Flower("Lily", "L09"), BigDecimal("24.95")),
            )
    }
}
