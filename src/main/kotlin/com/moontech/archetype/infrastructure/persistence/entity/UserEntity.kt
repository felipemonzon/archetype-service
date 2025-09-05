package com.moontech.archetype.infrastructure.persistence.entity

import com.moontech.archetype.domain.model.Authority
import com.moontech.archetype.domain.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId

/**
 * Entidad de Persistencia, que es distinta de la entidad de dominio.
 * Sigue el Principio de Segregación de Interfaces (ISP).
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@Entity(name = "users")
@Table(name = "users")
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
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_role")]
    )
    val roles: Set<RoleEntity>
) {
    fun toDomain(): User {
        return User(
            id = this.id,
            name = this.name,
            email = this.email,
            username = this.username,
            password = this.password,
            roles = roles.map { authority ->
                Authority(authority.id, authority.name)
            }.toSet()
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                username = user.username,
                password = user.password,
                roles = user.roles.map { profile ->
                    RoleEntity(profile.id, profile.name)
                }.toSet()
            )
        }
    }
}