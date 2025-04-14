package com.cashtrack.cashtrack_api.domain.dto.response

data class BalanceResponse(
    val totalIncomes:Double,
    val totalExpenses:Double,
    val balance:Double
)