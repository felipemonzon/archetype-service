package com.moontech.archetype.application.business.payments

import com.moontech.archetype.application.business.payments.country.MexicoPaymentFactory
import com.moontech.archetype.application.business.payments.country.SpainPaymentFactory
import com.moontech.archetype.application.enum.Country
import com.moontech.archetype.application.enum.PaymentType
import com.moontech.archetype.application.service.PaymentFactory
import java.math.BigDecimal

/**
 * El servicio de pago que depende de la interfaz de la fábrica, no de una fábrica concreta. Esto
 * demuestra el Principio de Inversión de Dependencia (DIP).
 *
 * @author Felipe Monzón
 * @since 03 sept, 2025
 */
class PaymentService(private val paymentFactories: List<PaymentFactory>) {
  // Método para procesar el pago. Aquí se delega la creación de la estrategia a la fábrica.
  fun processPayment(country: Country, paymentType: PaymentType, amount: BigDecimal) {
    val factory = getFactoryForCountry(country)
    val strategy = factory.getStrategy(paymentType)
    strategy.pay(amount)
  }

  // Método para obtener la fábrica correcta según el país.
  private fun getFactoryForCountry(country: Country): PaymentFactory {
    return when (country) {
      Country.SPAIN -> paymentFactories.first { it is SpainPaymentFactory }
      Country.MEXICO -> paymentFactories.first { it is MexicoPaymentFactory }
      else -> throw IllegalArgumentException("País no soportado: $country")
    }
  }
}
