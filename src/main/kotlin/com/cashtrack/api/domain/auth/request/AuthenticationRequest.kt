package com.cashtrack.api.domain.auth.request

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
