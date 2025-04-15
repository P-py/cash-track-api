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
    ),
    DATABASE_REGISTER_NOT_FOUND(
        code = "DATABASE_REGISTER_NOT_FOUND",
        message = "Registro não encontrado no banco",
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ACCESS_DENIED(
        code = "ACCESS_DENIED",
        message = "Você não tem permissão para acessar esse recurso.",
        httpStatus = HttpStatus.UNAUTHORIZED
    ),
    NOT_FOUND(
        code = "NOT_FOUND",
        message = "Não foi possível encontrar o registro.",
        httpStatus = HttpStatus.NOT_FOUND
    ),
}
