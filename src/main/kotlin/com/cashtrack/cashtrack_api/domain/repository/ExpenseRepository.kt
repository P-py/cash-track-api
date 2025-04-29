package com.cashtrack.cashtrack_api.domain.repository

import com.cashtrack.cashtrack_api.domain.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ExpenseRepository: JpaRepository<Expense, Long> {
    @Query(
        value = "SELECT * FROM expense WHERE label = :label",
        nativeQuery = true
    )
    fun getByLabel(@Param("label") label:String): List<Expense>
}