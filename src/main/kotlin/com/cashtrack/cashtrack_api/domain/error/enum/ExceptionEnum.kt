package com.cashtrack.cashtrack_api.domain.error.enum

import org.springframework.http.HttpStatus

enum class ExceptionEnum(
    val code: String = "UNKNOWN_API_ERROR",
    val message: String,
    val description: String? = null,
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
) {
    USER_ALREADY_EXISTS(
        code = "USER_ALREADY_EXISTS",
        message = "Não foi possível completar o cadastro. Uma conta já existe para esse e-mail.",
        httpStatus = HttpStatus.BAD_REQUEST
    ),
    BAD_ARGUMENTS(
        code = "BAD_ARGUMENTS",
        message = "Erro ao validar os argumentos da request.",
        httpStatus = HttpStatus.BAD_REQUEST
    ),
    UNKNOWN_ERROR(
        code = "UNKNOWN_ERROR",
        message = "Ocorreu um erro desconhecido.",
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    )
}
