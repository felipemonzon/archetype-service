package com.moontech.archetype.application.business

import com.moontech.archetype.domain.model.User
import com.moontech.archetype.domain.repository.UserRepository
import com.moontech.archetype.infrastructure.model.UserDto
import org.springframework.stereotype.Service

/**
 * Implementación del caso de uso. Sigue SRP y DIP. Depende de la interfaz del repositorio, no de la
 * implementación concreta.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 */
@Service
class UserCreationService(private val userRepository: UserRepository) {

  // Método principal del caso de uso.
  fun createNewUser(user: UserDto): User {
    require(this.userRepository.findByEmail(user.email).isPresent) {
      throw IllegalArgumentException("El correo electrónico ya existe.")
    }
    val newUser = user.toDomain()
    return this.userRepository.save(newUser)
  }
}
