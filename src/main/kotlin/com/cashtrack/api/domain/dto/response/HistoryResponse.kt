package com.cashtrack.api.domain.dto.response

import java.time.LocalDateTime

data class HistoryResponse(
    val label: String,
    val dateCreated: LocalDateTime,
    val value: Double,
    val source: String,
)
