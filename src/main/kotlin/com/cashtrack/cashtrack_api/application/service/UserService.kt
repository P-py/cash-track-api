package com.cashtrack.cashtrack_api.application.service

import com.cashtrack.cashtrack_api.domain.adapter.UserAdapter
import com.cashtrack.cashtrack_api.domain.auth.request.UserRegisterRequest
import com.cashtrack.cashtrack_api.domain.auth.response.UserResponse
import com.cashtrack.cashtrack_api.domain.error.exception.UserAlreadyExistsException
import com.cashtrack.cashtrack_api.domain.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service

@Service
class UserService(
    private val usersRepository: UserRepository,
    private val mapper: UserAdapter,
) {
    @Caching(evict = [CacheEvict("UsersList", allEntries = true), CacheEvict("UserDetails", allEntries = true)])
    fun registerNewUser(newUser: UserRegisterRequest): UserResponse {
        val new = mapper.mapEntry(newUser)
        if (usersRepository.findByEmail(new.email) == null) {
            usersRepository.save(new)
            return mapper.mapView(new)
        } else throw UserAlreadyExistsException(
            message = "An user for this e-mail already exists."
        )
    }
}