package com.cashtrack.cashtrack_api.application.service

import com.cashtrack.cashtrack_api.domain.UserCashtrack
import com.cashtrack.cashtrack_api.domain.error.exception.DatabaseRegisterNotFoundException
import com.cashtrack.cashtrack_api.domain.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

typealias ApplicationUser = UserCashtrack

@Service
class CustomUserDetailsService(
    private val usersRepository:UserRepository,
): UserDetailsService {
    override fun loadUserByUsername(username:String): UserDetails =
        usersRepository.findByEmail(username)
            .orElseThrow {
                DatabaseRegisterNotFoundException(
                    message = "User does not exist for this e-mail."
                )
            }
            .mapToUserDetails()

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
}