package com.cashtrack.cashtrack_api.domain

import com.cashtrack.cashtrack_api.domain.enum.IncomeType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Income(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var incomeLabel: String = "",
    var value: Double = 0.0,
    @Enumerated(value = EnumType.STRING)
    var type: IncomeType = IncomeType.GIFT,
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    var userCashtrack: UserCashtrack? = null,
    var lastUpdatedAt: LocalDateTime? = null
)