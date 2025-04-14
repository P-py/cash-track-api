package com.cashtrack.cashtrack_api.domain.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserRegisterRequest(
    @field:NotEmpty(message = "Email must not be empty.")
    @field:NotBlank(message = "Email must not be blank.")
    @field:Size(min = 5, max = 100, message = "Email size must be between 5 and 100 characters.")
    @field:Email(message = "Invalid email format.")
    val email:String,
    @field:NotEmpty(message = "Username must not be empty.")
    @field:NotBlank(message = "Username must not be blank.")
    @field:Size(min = 3, max = 40, message = "Username size must be between 3 and 40.")
    val username:String,
    @field:NotEmpty(message = "Password must no be empty.")
    @field:NotBlank(message = "Password must no be blank.")
    @field:Size(min = 8, max = 100, message = "Password size must be at least 8 characters long")
    val password:String
)