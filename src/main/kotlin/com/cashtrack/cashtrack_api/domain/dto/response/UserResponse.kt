package com.cashtrack.cashtrack_api.domain.dto

import java.io.Serializable

data class UserResponse(
    val id:Long?,
    val username:String
): Serializable