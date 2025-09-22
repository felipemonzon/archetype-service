package com.moontech.archetype.infrastructure.security.property

/**
 * Propiedades para configuración de seguridad.
 *
 * @author Felipe Monzón
 * @since 04 sept, 2025
 */
data class SecurityProperties(
  /** Llave del JWT. */
  val jwtKey: String,
  /** Tiempo de vida del token. */
  val jwtLifeTime: Long,
  /** Ruta para la autenticación de usuarios. */
  val userAuthenticationPath: String,
  /** Lista de dominios permitidos. */
  val cors: MutableList<String>,
)
