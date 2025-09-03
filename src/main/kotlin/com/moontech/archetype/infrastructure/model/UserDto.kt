package com.moontech.archetype.infrastructure.model

import com.moontech.archetype.domain.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * DTO para manejar la entrada y salida de datos en la API.
 * No forma parte del dominio.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 **/
class UserDto(
    @field:NotBlank
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String,
) {
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
        fun fromDomain(user: User): UserDto {
            return UserDto(
                name = user.name, email = user.email,
                username = user.username,
                password = user.password
            )
        }
    }
}