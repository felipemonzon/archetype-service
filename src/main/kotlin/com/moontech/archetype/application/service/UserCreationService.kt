package com.moontech.archetype.application.service

import com.moontech.archetype.domain.model.User
import com.moontech.archetype.domain.repository.UserRepository
import org.springframework.stereotype.Service

/**
 * Implementación del caso de uso. Sigue SRP y DIP.
 * Depende de la interfaz del repositorio, no de la implementación concreta.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 */
@Service
class UserCreationService(private val userRepository: UserRepository) {

    // Método principal del caso de uso.
    fun createNewUser(name: String, email: String, username: String, password: String): User {
        require(this.userRepository.findByEmail(email).isPresent) {
            throw IllegalArgumentException("El correo electrónico ya existe.")
        }
        val newUser = User(
            name = name, email = email,
            username = username,
            password = password
        )
        return this.userRepository.save(newUser)
    }
}