package com.cashtrack.api.domain.error.exception

import com.cashtrack.api.domain.error.enum.ExceptionEnum
import org.springframework.http.HttpStatus

open class CustomException(
    open val errorEnum: ExceptionEnum,
    open val code: String = errorEnum.code ?: errorEnum.name,
    override val message: String = errorEnum.message,
    open val description: String? = errorEnum.description,
    open val httpStatus: HttpStatus = errorEnum.httpStatus,
) : RuntimeException(message)
