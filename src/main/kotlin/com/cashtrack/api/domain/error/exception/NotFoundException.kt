package com.cashtrack.api.domain.error.exception

import com.cashtrack.api.domain.error.enum.ExceptionEnum

open class NotFoundException(
    override val errorEnum: ExceptionEnum = ExceptionEnum.NOT_FOUND,
    val data: String? = null,
    override val code: String = errorEnum.code,
    override val message: String = "Não foi possível encontrar $data",
) : CustomException(errorEnum)
