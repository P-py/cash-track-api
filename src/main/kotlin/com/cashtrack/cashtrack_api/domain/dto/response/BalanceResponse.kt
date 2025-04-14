package com.cashtrack.cashtrack_api.domain.dto

data class BalanceResponse(
    val totalIncomes:Double,
    val totalExpenses:Double,
    val balance:Double
)