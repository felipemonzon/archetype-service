package com.moontech.archetype.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 *  Interfaz de Spring Data JPA, específica de la capa de infraestructura.
 *  Aquí se aplican las convenciones de nombres de métodos de Spring Data.
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@Repository
interface UserSpringDataRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): Optional<UserEntity>
}