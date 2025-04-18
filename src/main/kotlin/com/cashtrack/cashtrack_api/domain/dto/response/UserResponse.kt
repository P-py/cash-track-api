package com.cashtrack.cashtrack_api.domain.dto.response

import java.io.Serializable

@Suppress("SerialVersionUIDInSerializableClass")
data class UserResponse(
    val id:Long?,
    val username:String,
    val email: String,
): Serializable