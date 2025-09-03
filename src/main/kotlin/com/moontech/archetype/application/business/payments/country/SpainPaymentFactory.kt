package com.moontech.archetype.application.business.payments.country

import com.moontech.archetype.application.business.payments.type.CreditCardPayment
import com.moontech.archetype.application.business.payments.type.PayPalPayment
import com.moontech.archetype.application.enum.PaymentType
import com.moontech.archetype.application.service.PaymentFactory
import com.moontech.archetype.application.service.PaymentStrategy

/**
 * Fábricas que crean estrategias de pago específicas para cada país.
 *
 *  @author Felipe Monzón
 *  @since 03 sept, 2025
 */
class SpainPaymentFactory(
    private val creditCardPayment: CreditCardPayment,
    private val payPalPayment: PayPalPayment
) : PaymentFactory {
    override fun getStrategy(paymentType: PaymentType): PaymentStrategy {
        return when (paymentType) {
            PaymentType.CREDIT_CARD -> creditCardPayment
            PaymentType.PAYPAL -> payPalPayment
            else -> throw IllegalArgumentException("Tipo de pago no soportado en España: $paymentType")
        }
    }
}