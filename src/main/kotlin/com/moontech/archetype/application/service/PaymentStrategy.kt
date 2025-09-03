package com.moontech.archetype.application.service

import java.math.BigDecimal

/**
 * La interfaz de estrategia común para todos los tipos de pago.
 *
 *  @author Felipe Monzón
 *  @since 03 sept, 2025
 */
fun interface PaymentStrategy {
    fun pay(amount: BigDecimal)
}