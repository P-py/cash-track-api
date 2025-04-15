package com.cashtrack.cashtrack_api.domain.dto.request

import com.cashtrack.cashtrack_api.domain.enum.IncomeType
import jakarta.validation.constraints.*

data class IncomeUpdateRequest(
    @field:NotNull(message = "You must specify an income to be updated.")
    val id: Long,
    @field:NotEmpty(message = "Update label must not be empty.")
    @field:NotBlank(message = "Update label must not be empty.")
    @field:Size(min = 3, max = 30, message = "Label size must be between 3 and 30.")
    val incomeLabel: String,
    @field:NotNull(message = "Value must not be empty.")
    @field:Positive(message = "Value must be positive.")
    val value: Double,
    @field:NotNull(message = "Expense type must be specified.")
    val type: IncomeType,
)
