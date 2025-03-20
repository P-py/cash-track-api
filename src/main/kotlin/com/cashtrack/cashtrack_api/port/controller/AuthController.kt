package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.AuthenticationService
import com.cashtrack.cashtrack_api.domain.auth.request.AuthenticationRequest
import com.cashtrack.cashtrack_api.domain.auth.response.AuthenticationResponse
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController(
    private val authenticationService:AuthenticationService,
){
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        val authResponse = authenticationService.authentication(authRequest)
        return authResponse
    }
}