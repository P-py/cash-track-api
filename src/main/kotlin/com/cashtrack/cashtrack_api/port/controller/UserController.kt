package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.UserService
import com.cashtrack.cashtrack_api.domain.auth.request.UserRegisterRequest
import com.cashtrack.cashtrack_api.domain.auth.response.UserResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@CrossOrigin
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
class UserController(private val service: UserService) {
    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid newUser: UserRegisterRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserResponse> {
        val userResponse = service.registerNewUser(newUser)
        val uri = uriBuilder.path("/users/${userResponse.id}")
            .build()
            .toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }
}