package com.moontech.archetype.infrastructure.persistence

import com.moontech.archetype.domain.model.User
import com.moontech.archetype.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.Optional

/**
 *  Implementación de la interfaz del dominio (DIP).
 *  Utiliza Spring Data JPA para la persistencia.
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@Repository
class JpaUserRepository(private val jpaRepository: UserSpringDataRepository) : UserRepository {
    override fun save(user: User): User {
        val entity = UserEntity.fromDomain(user)
        val savedEntity = jpaRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun findById(id: Long): Optional<User> {
        return jpaRepository.findById(id).map { it.toDomain() }
    }

    override fun findByEmail(email: String): Optional<User> {
        return jpaRepository.findByEmail(email).map { it.toDomain() }
    }
}