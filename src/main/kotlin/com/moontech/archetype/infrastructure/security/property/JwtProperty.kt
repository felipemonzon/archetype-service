package com.moontech.archetype.infrastructure.security.property

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Propiedades para configuración del JWT.
 *
 * @author Felipe Monzón
 * @since 04 sept, 2025
 */
@Configuration
class JwtProperty(
  /** Llave del JWT. */
  @param:Value("\${security.jwt.key}") private val secretKey: String,
  @param:Value("\${security.jwt.lifeTime}") private val expiration: Long,
  /** Ruta para la autenticación de usuarios. */
  @param:Value("\${api.uri.data.authentication}") private val userAuthenticationPath: String,
  /** Lista de dominios permitidos. */
  @param:Value("\${security.cors.origins}") private val cors: MutableList<String>,
) {
  @Bean
  fun loadSecurityProperties(): SecurityProperties {
    return SecurityProperties(
      jwtKey = this.secretKey,
      jwtLifeTime = this.expiration,
      userAuthenticationPath = this.userAuthenticationPath,
      cors = this.cors,
    )
  }
}
