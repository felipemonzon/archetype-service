package com.moontech.archetype.infrastructure.model

/**
 * Clase DTO para login.
 *
 * @author Felipe Monzón
 * @since 05 sept, 2025
 */
data class AuthDto(
    val username: String,
    val password: String
)