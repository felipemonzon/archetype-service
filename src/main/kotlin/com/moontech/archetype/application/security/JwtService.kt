package com.moontech.archetype.application.security

import com.moontech.archetype.infrastructure.security.property.SecurityProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey
import org.springframework.stereotype.Component

/**
 * Service to handle JWT token generation and validation.
 *
 * @author Felipe Monzón
 * @since 04 sept, 2025
 */
@Component
class JwtService(private val securityProperties: SecurityProperties) {
  private val secretKey: SecretKey =
    Keys.hmacShaKeyFor(this.securityProperties.jwtKey.toByteArray(StandardCharsets.UTF_8))

  // Generates a JWT token for the given user details
  fun generateToken(username: String): String {
    return Jwts.builder()
      .subject(username)
      .issuedAt(Date(System.currentTimeMillis()))
      .expiration(
        Date(
          System.currentTimeMillis() +
            TimeUnit.MINUTES.toMillis(this.securityProperties.jwtLifeTime)
        )
      )
      .signWith(secretKey)
      .compact()
  }

  // Extracts the username from a JWT token
  fun extractUsername(token: String): String {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.subject
  }

  // Validates a JWT token's expiration and signature
  fun isTokenValid(token: String, username: String): Boolean {
    return try {
      val claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload
      val expiration = claims.expiration
      expiration.after(Date()) && claims.subject == username
    } catch (e: Exception) {
      false
    }
  }
}
