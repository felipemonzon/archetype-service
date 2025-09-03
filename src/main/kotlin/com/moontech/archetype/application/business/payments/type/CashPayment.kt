package com.moontech.archetype.application.business.payments.type

import com.moontech.archetype.application.service.PaymentStrategy
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * Clases que implementan la interfaz PaymentStrategy,
 * Representan las diferentes formas de pago.
 *
 * @author Felipe Monzón
 * @since 03 sept, 2025
 */
@Component
class CashPayment: PaymentStrategy {
    override fun pay(amount: BigDecimal) {
        println("Procesando pago en efectivo por $amount.")
    }
}