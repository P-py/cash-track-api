package com.cashtrack.cashtrack_api.domain

import com.cashtrack.cashtrack_api.domain.enum.ExpenseType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Expense(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    override var label: String = "",
    override var value: Double = 0.0,
    @Enumerated(value = EnumType.STRING)
    var type: ExpenseType = ExpenseType.INVESTMENTS,
    override val dateCreated: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    override var userCashtrack: UserCashtrack? = null,
    override var lastUpdatedAt: LocalDateTime? = null,
    override val source: String = "expense"
) : Trackable