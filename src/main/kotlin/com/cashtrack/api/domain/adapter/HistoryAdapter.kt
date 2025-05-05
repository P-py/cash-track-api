package com.cashtrack.api.domain.adapter

import com.cashtrack.api.domain.Trackable
import com.cashtrack.api.domain.dto.response.HistoryResponse
import org.springframework.stereotype.Component

@Component
class HistoryAdapter {
    fun mapView(c: Trackable): HistoryResponse {
        return HistoryResponse(
            label = c.label,
            value = c.value,
            dateCreated = c.dateCreated,
            source = c.source,
        )
    }
}
