package com.cashtrack.api.domain.error.exception

data class ErrorResponse(
    val code: String = "UNKNOWN_ERROR",
    val message: String = "Ops! Um erro inesperado aconteceu.",
    val description: String? = "Tente novamente mais tarde",
)
