package com.cashtrack.api.domain.dto.request

import com.cashtrack.api.domain.enum.ExpenseType
import jakarta.validation.constraints.*

data class ExpenseRegisterRequest(
    @field:NotEmpty(message = "Label must not be empty.")
    @field:NotBlank(message = "Label must not be blank.")
    @field:Size(min = 3, max = 30, message = "Size for label must be between 3 and 30.")
    val expenseLabel: String,
    @field:NotNull(message = "Value must not be empty.")
    @field:Positive(message = "Value must be positive.")
    val value: Double,
    @field:NotNull(message = "Type must not be empty.")
    val type: ExpenseType,
)
