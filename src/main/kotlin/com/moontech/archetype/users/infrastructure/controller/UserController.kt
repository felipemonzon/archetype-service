package com.moontech.archetype.users.infrastructure.controller


import com.moontech.archetype.users.application.business.UsersService
import com.moontech.archetype.users.infrastructure.model.UserDTO
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
@RequestMapping("/api/users")
class UserController(private val userService: UsersService) {
    @PostMapping
    fun createUser(@Valid @RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        return try {
            ResponseEntity(UserDTO.fromDomain(userService.saveUser(userDTO)), HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}