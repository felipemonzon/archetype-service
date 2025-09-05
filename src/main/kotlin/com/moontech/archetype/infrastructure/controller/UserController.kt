package com.moontech.archetype.infrastructure.controller

import com.moontech.archetype.application.business.UserCreationService
import com.moontech.archetype.infrastructure.model.UserDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controlador REST. Sigue el Principio de Responsabilidad Única (SRP).
 * Solo maneja peticiones HTTP y delega la lógica de negocio al servicio.
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@RestController
@RequestMapping("/users")
class UserController(private val userCreationService: UserCreationService) {
    @PostMapping
    fun createUser(@Valid @RequestBody userDTO: UserDto): ResponseEntity<UserDto> {
        return try {
            val newUser = userCreationService.createNewUser(userDTO)
            ResponseEntity(UserDto.fromDomain(newUser), HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}