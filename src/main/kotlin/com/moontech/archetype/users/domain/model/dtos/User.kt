package com.moontech.archetype.users.domain.model.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

/**
 * Entidad de dominio pura, sin anotaciones de persistencia.
 * Sigue el Principio de Responsabilidad Única (SRP),
 * ya que solo representa la información de un usuario.
 *
 * @author Felipe Monzón
 * @since 02 sept, 2025
 **/
data class User(
    var id: Long? = null,
    val name: String,
    val email: String,
    val username: String,
    @JsonIgnore
    val password: String,
    val creationDate: LocalDateTime = LocalDateTime.now()
) {

    fun updateEmail(newEmail: String) = this.copy(email = newEmail)
}