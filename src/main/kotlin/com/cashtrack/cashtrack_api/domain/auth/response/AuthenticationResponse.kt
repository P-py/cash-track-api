package com.cashtrack.cashtrack_api.domain.auth.response

data class AuthenticationResponse(
    val accessToken:String,
    val userId:Long?
)