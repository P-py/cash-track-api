package com.cashtrack.api.domain.error.exception

import com.cashtrack.api.domain.error.enum.ExceptionEnum

open class UserAlreadyExistsException(
    override val errorEnum: ExceptionEnum = ExceptionEnum.USER_ALREADY_EXISTS,
    val data: String? = null,
    override val code: String = errorEnum.code,
    override val message: String = "Não foi possível processar $data",
) : CustomException(errorEnum)
