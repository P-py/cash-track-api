package com.cashtrack.cashtrack_api.domain.adapter

import com.cashtrack.cashtrack_api.domain.Trackable
import com.cashtrack.cashtrack_api.domain.dto.response.HistoryResponse
import org.springframework.stereotype.Component

@Component
class HistoryAdapter {
    fun mapView(c: Trackable): HistoryResponse {
        return HistoryResponse(
            label = c.label,
            value = c.value,
            dateCreated = c.dateCreated,
            source = c.source
        )
    }
}