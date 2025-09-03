package com.moontech.archetype.infrastructure.persistence

import com.moontech.archetype.domain.model.User
import jakarta.persistence.*
import org.hibernate.annotations.NaturalId

/**
 * Entidad de Persistencia, que es distinta de la entidad de dominio.
 * Sigue el Principio de Segregación de Interfaces (ISP).
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@Entity(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "first_name", nullable = false, length = 50)
    val name: String,
    @Column(name = "email_address", nullable = false)
    val email: String,
    @NaturalId
    @Column(name = "username", nullable = false, length = 20, unique = true)
    val username: String,
    @Column(name = "password", nullable = false, length = 200)
    val password: String
) {
    fun toDomain(): User {
        return User(
            id = this.id, name = this.name, email = this.email,
            username = this.username,
            password = this.password,
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id, name = user.name, email = user.email,
                username = user.username,
                password = user.password
            )
        }
    }
}