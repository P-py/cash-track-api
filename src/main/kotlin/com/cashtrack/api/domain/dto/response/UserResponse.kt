package com.cashtrack.api.domain.dto.response

import java.io.Serializable

@Suppress("SerialVersionUIDInSerializableClass")
data class UserResponse(
    val id: Long?,
    val username: String,
    val email: String,
) : Serializable
