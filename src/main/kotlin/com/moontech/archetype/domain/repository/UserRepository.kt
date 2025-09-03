package com.moontech.archetype.domain.repository

import com.moontech.archetype.domain.model.User
import java.util.*

/**
 * Abstracción del acceso a datos (Principio de Inversión de Dependencia - DIP).
 * La lógica de negocio no se preocupa de la implementación.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 */
interface UserRepository {
    fun save(user: User): User
    fun findById(id: Long): Optional<User>
    fun findByEmail(email: String): Optional<User>
}