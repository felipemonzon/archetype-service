package com.moontech.archetype.users.application.business

import com.moontech.archetype.users.domain.model.dtos.User
import com.moontech.archetype.users.infrastructure.model.UserDTO


interface UsersService {
    fun saveUser(user: UserDTO): User
}