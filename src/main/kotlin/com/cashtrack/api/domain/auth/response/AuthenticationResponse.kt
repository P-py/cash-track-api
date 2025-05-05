package com.cashtrack.api.domain.auth.response

data class AuthenticationResponse(
    val accessToken: String,
    val userId: Long?,
)
