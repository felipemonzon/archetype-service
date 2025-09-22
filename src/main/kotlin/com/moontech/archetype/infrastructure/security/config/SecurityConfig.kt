package com.moontech.archetype.infrastructure.security.config

import com.moontech.archetype.infrastructure.security.filter.JwtAuthenticationFilter
import com.moontech.archetype.infrastructure.security.property.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Configuración para spring security
 *
 * @author Felipe Monzón
 * @since 04 sept, 2025
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
  private val jwtAuthFilter: JwtAuthenticationFilter,
  private val userDetailsService: UserDetailsService,
  private val securityProperties: SecurityProperties,
) {
  /** Define la cadena de filtros de seguridad. */
  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http
      .csrf { it.disable() }
      .cors { it.configurationSource(corsConfigurationSource()) }
      .headers {
        it.xssProtection { xss ->
          xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
        }
        it.contentSecurityPolicy { cps -> cps.policyDirectives("default-src 'self'") }
        it.httpStrictTransportSecurity { hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(36L) }
      }
      .authorizeHttpRequests { authorize ->
        authorize
          .requestMatchers(this.securityProperties.userAuthenticationPath)
          .permitAll()
          .anyRequest()
          .authenticated()
      }
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authenticationProvider(authenticationProvider())
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

    return http.build()
  }

  /**
   * Configura el proveedor de autenticación con el servicio de detalles de usuario y el codificador
   * de contraseñas.
   */
  @Bean
  fun authenticationProvider(): DaoAuthenticationProvider {
    val authProvider = DaoAuthenticationProvider(userDetailsService)
    authProvider.setPasswordEncoder(passwordEncoder())
    return authProvider
  }

  /** Expone el AuthenticationManager para su uso en el controlador de autenticación. */
  @Bean
  fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
    return config.authenticationManager
  }

  /**
   * Define un codificador de contraseñas. BCrypt es el estándar recomendado para hashear
   * contraseñas.
   */
  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  /**
   * Configura el origen de CORS para permitir solicitudes desde cualquier origen y con los métodos
   * y cabeceras necesarios.
   */
  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource {
    val configuration = CorsConfiguration()
    configuration.allowedOriginPatterns = this.securityProperties.cors
    configuration.allowedMethods =
      listOf(
        HttpMethod.GET.name(),
        HttpMethod.POST.name(),
        HttpMethod.PUT.name(),
        HttpMethod.DELETE.name(),
        HttpMethod.OPTIONS.name(),
      )
    configuration.allowedHeaders = listOf(CorsConfiguration.ALL)
    configuration.allowCredentials = true
    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", configuration)
    return source
  }
}
