package com.cashtrack.cashtrack_api.domain.auth.response

data class BalanceResponse(
    val totalIncomes:Double,
    val totalExpenses:Double,
    val balance:Double
)