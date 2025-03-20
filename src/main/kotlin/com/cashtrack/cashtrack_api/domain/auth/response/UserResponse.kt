package com.cashtrack.cashtrack_api.domain.auth.response

import java.io.Serializable

data class UserResponse(
    val id:Long?,
    val username:String
): Serializable