package com.moontech.archetype.users.application.business.impl

import com.moontech.archetype.users.application.business.UsersService
import com.moontech.archetype.users.domain.model.dtos.User
import com.moontech.archetype.users.domain.repository.UserRepository
import com.moontech.archetype.users.infrastructure.model.UserDTO
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UsersService {
    override fun saveUser(user: UserDTO): User {
        this.userRepository.findByEmail(user.email).getOrElse { return this.userRepository.save(user.toDomain()) }.let { throw IllegalArgumentException("El correo electrónico ya existe.") }
    }
}