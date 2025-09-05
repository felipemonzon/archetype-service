package com.moontech.archetype.application.security

import com.moontech.archetype.infrastructure.persistence.repository.UserSpringDataRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

/**
 *  Servicio para implementar la capa de autenticación por medio de base de datos.
 *
 *  @author Felipe Monzón
 *  @since 02 sept, 2025
 */
@Service
class AuthenticationDetailsService(
    private val userRepository: UserSpringDataRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        val userEntity = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Usuario no encontrado: $username") }

        val authorities = userEntity.roles.map { role ->
            SimpleGrantedAuthority("ROLE_${role.name}")
        }.toSet()

        return SpringUser(
            userEntity.username,
            userEntity.password,
            authorities
        )
    }

}