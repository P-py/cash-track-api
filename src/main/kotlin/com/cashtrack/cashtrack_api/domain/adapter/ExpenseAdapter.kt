package com.cashtrack.cashtrack_api.domain.adapter

import com.cashtrack.cashtrack_api.domain.Expense
import com.cashtrack.cashtrack_api.domain.dto.request.ExpenseRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.response.ExpenseResponse
import org.springframework.stereotype.Component

@Component
class ExpenseAdapter : Adapter<Expense, ExpenseResponse, ExpenseRegisterRequest> {
    override fun mapEntry(e: ExpenseRegisterRequest):Expense {
        return Expense(
            expenseLabel = e.expenseLabel,
            value = e.value,
            type = e.type,
            userCashtrack = null
        )
    }

    override fun mapView(c:Expense): ExpenseResponse {
        return ExpenseResponse(
            id = c.id,
            expenseLabel = c.expenseLabel,
            value = c.value,
            type = c.type,
            dateCreated = c.dateCreated,
            lastUpdatedAt = c.lastUpdatedAt
        )
    }
}