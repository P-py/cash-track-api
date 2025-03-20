package com.cashtrack.cashtrack_api.domain.auth.request

data class AuthenticationRequest(
    val email:String,
    val password:String
)