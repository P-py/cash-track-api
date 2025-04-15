package com.cashtrack.cashtrack_api.domain.error.auth

import com.cashtrack.cashtrack_api.domain.error.enum.ExceptionEnum
import com.cashtrack.cashtrack_api.domain.error.exception.CustomException

open class AccessDeniedException(
    override val errorEnum: ExceptionEnum = ExceptionEnum.ACCESS_DENIED,
    val data: String? = null,
    override val code: String = errorEnum.code,
    override val message: String = "Você não tem permissão para acessar esse recurso",
) : CustomException(errorEnum)