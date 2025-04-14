package com.cashtrack.cashtrack_api.application.service

import com.cashtrack.cashtrack_api.domain.auth.request.AuthenticationRequest
import com.cashtrack.cashtrack_api.domain.auth.response.AuthenticationResponse
import com.cashtrack.cashtrack_api.domain.error.exception.DatabaseRegisterNotFoundException
import com.cashtrack.cashtrack_api.domain.repository.UserRepository
import com.cashtrack.cashtrack_api.port.config.property.JwtProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService:CustomUserDetailsService,
    private val tokenService:TokenService,
    private val jwtProperties:JwtProperties,
    private val usersRepository: UserRepository,
){
    fun authentication(authRequest:AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthenticationResponse(
            accessToken = accessToken,
            userId = usersRepository.findByEmail(user.username)
                .orElseThrow {
                    DatabaseRegisterNotFoundException(
                        message = "User does not exist for this e-mail."
                    )
                }
                .id
        )
    }
}