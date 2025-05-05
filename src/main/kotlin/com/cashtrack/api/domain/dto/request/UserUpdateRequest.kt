package com.cashtrack.api.domain.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UserUpdateRequest(
    @field:Size(min = 5, max = 100, message = "Email size must be between 5 and 100 characters.")
    @field:Email(message = "Invalid email format.")
    val email: String?,
    @field:Size(min = 3, max = 40, message = "Username size must be between 3 and 40.")
    val username: String?,
)
