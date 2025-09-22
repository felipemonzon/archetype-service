package com.moontech.archetype.infrastructure.persistence.repository

import com.moontech.archetype.infrastructure.persistence.entity.UserEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Interfaz de Spring Data JPA, específica de la capa de infraestructura. Aquí se aplican las
 * convenciones de nombres de métodos de Spring Data.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 */
@Repository
interface UserSpringDataRepository : JpaRepository<UserEntity, Long> {
  fun findByEmail(email: String): Optional<UserEntity>

  fun findByUsername(username: String?): Optional<UserEntity>
}
