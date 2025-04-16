package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.AuthenticationService
import com.cashtrack.cashtrack_api.domain.auth.request.AuthenticationRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController(
    private val authenticationService:AuthenticationService,
){
    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest,
        response: HttpServletResponse,
    ): ResponseEntity<Unit> {
        val authResponse = authenticationService.authentication(authRequest)
        response.setHeader(HttpHeaders.AUTHORIZATION, authResponse)

        return ResponseEntity.noContent().build()
    }
}