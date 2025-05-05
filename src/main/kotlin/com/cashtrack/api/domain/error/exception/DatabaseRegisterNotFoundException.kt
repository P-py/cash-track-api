package com.cashtrack.api.domain.error.exception

import com.cashtrack.api.domain.error.enum.ExceptionEnum

open class DatabaseRegisterNotFoundException(
    override val errorEnum: ExceptionEnum = ExceptionEnum.DATABASE_REGISTER_NOT_FOUND,
    val data: String? = null,
    override val code: String = errorEnum.code,
    override val message: String = "Registro não encontrado $data",
) : CustomException(errorEnum)
