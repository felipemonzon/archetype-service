package com.moontech.archetype.infrastructure.controller

import com.moontech.archetype.application.security.JwtService
import com.moontech.archetype.infrastructure.model.AuthDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * API para login
 *
 * @author Felipe Monzón
 * @since 05 sept, 2025
 */
@RestController
@RequestMapping("/users/authentication")
class LoginController(
  private val authenticationManager: AuthenticationManager,
  private val jwtService: JwtService,
) {
  /**
   * Endpoint para autenticar a un usuario y generar un JWT.
   *
   * @param authRequest Objeto que contiene las credenciales del usuario.
   * @return ResponseEntity con el JWT si la autenticación es exitosa.
   */
  @PostMapping
  fun login(@RequestBody authRequest: AuthDto): ResponseEntity<Unit> {
    return try {
      val authentication =
        authenticationManager.authenticate(
          UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        )
      val jwtToken = jwtService.generateToken(authentication.name)
      ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer $jwtToken").build()
    } catch (e: AuthenticationException) {
      ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
  }
}
