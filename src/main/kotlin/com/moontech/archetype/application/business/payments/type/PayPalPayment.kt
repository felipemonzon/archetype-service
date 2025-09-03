package com.moontech.archetype.application.business.payments.type

import com.moontech.archetype.application.service.PaymentStrategy
import java.math.BigDecimal

/**
 * Clases que implementan la interfaz PaymentStrategy,
 * Representan las diferentes formas de pago.
 *
 * @author Felipe Monzón
 * @since 03 sept, 2025
 */
class PayPalPayment: PaymentStrategy {
    override fun pay(amount: BigDecimal) {
        println("Procesando pago con PayPal por $amount.")
    }
}