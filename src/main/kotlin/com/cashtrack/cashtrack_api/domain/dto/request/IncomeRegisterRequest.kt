package com.cashtrack.cashtrack_api.domain.dto.request

import com.cashtrack.cashtrack_api.domain.enum.IncomeType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class IncomeRegisterRequest(
    @field:NotEmpty(message = "Label must not be empty.")
    @field:NotBlank(message = "Label must not be blank.")
    @field:Size(min = 3, max = 30, message = "Label size must be between 3 and 30.")
    val incomeLabel: String,
    @field:NotNull(message = "Value must not be empty.")
    @field:Positive
    val value: Double,
    @field:NotNull(message = "Type must not be empty.")
    val type: IncomeType
)
