package com.cashtrack.cashtrack_api.domain.error

import com.cashtrack.cashtrack_api.domain.error.enum.ExceptionEnum
import com.cashtrack.cashtrack_api.domain.error.exception.CustomException
import com.cashtrack.cashtrack_api.domain.error.exception.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknownErrorHandler(exception: Exception): ErrorResponse {
        logger.error(
            "Erro desconhecido",
            exception,
        )
        return ErrorResponse(
            ExceptionEnum.UNKNOWN_ERROR.name,
            ExceptionEnum.UNKNOWN_ERROR.message,
            ExceptionEnum.UNKNOWN_ERROR.description,
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ErrorResponse {
        logger.error(
            "Erro ao validar campo(s) da requisicao",
            exception,
        )

        val errorMessage = exception.bindingResult.fieldErrors
            .firstNotNullOfOrNull { it.defaultMessage }
            ?: ExceptionEnum.BAD_ARGUMENTS.message

        return ErrorResponse(
            code = ExceptionEnum.BAD_ARGUMENTS.name,
            message = errorMessage,
            description = null
        )
    }

    @ExceptionHandler(CustomException::class)
    fun coreThrowable(exception: CustomException): ResponseEntity<ErrorResponse> {
        logger.error("Custom error: {${exception::class.java.name}(${exception.code})}.", exception)
        return ResponseEntity(
            ErrorResponse(
                exception.code,
                exception.message,
                exception.description,
            ),
            exception.httpStatus,
        )
    }
}
