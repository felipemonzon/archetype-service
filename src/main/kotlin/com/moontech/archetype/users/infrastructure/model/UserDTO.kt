package com.moontech.archetype.users.infrastructure.model

import com.moontech.archetype.users.domain.model.dtos.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class UserDTO(
    @field:NotBlank
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String) {
    // Método para convertir a la entidad de dominio.
    fun toDomain(): User {
        return User(
            name = this.name, email = this.email,
            username = this.username,
            password = this.password,
        )
    }

    companion object {
        // Método para convertir de la entidad de dominio a DTO.
        fun fromDomain(user: User): UserDTO {
            return UserDTO(
                name = user.name, email = user.email,
                username = user.username,
                password = user.password
            )
        }
    }
}