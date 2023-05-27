package it.jasin

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class FlowerShopTest {


    @Test
    fun `should correctly generate price breakdown for order`() {
        val bundler = mockk<Bundler>()
        val accountant = mockk<Accountant>()
        val shop = FlowerShop(bundler, accountant)
        val mockedBundles = listOf(Bundle(5, Flower("aFlower", "aCode"), BigDecimal("7.99")))
        val mockedPriceBreakdown = "aPriceBreakdown"
        every { bundler.getBundlesFor(any())} returns mockedBundles
        every { accountant.calculatePriceBreakdownOf(mockedBundles) } returns mockedPriceBreakdown

        assertThat(shop.generateInvoiceFor("any order")).isEqualTo(mockedPriceBreakdown)
    }
}