package com.moontech.archetype.application.service

import com.moontech.archetype.application.enum.PaymentType

/**
 * La interfaz de fábrica que abstrae la creación de las estrategias.
 *
 * @author Felipe Monzón
 * @since 03 sept, 2025
 */
fun interface PaymentFactory {
  fun getStrategy(paymentType: PaymentType): PaymentStrategy
}
