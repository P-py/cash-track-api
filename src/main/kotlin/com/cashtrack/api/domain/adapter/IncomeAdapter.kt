package com.cashtrack.api.domain.adapter

import com.cashtrack.api.domain.Income
import com.cashtrack.api.domain.dto.request.IncomeRegisterRequest
import com.cashtrack.api.domain.dto.response.IncomeResponse
import org.springframework.stereotype.Component

@Component
class IncomeAdapter : Adapter<Income, IncomeResponse, IncomeRegisterRequest> {
    override fun mapView(c: Income): IncomeResponse {
        return IncomeResponse(
            id = c.id,
            incomeLabel = c.label,
            value = c.value,
            type = c.type,
            dateCreated = c.dateCreated,
            lastUpdatedAt = c.lastUpdatedAt,
        )
    }

    override fun mapEntry(e: IncomeRegisterRequest): Income {
        return Income(
            label = e.incomeLabel,
            value = e.value,
            type = e.type,
            userCashtrack = null,
        )
    }
}
