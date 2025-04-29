package com.cashtrack.cashtrack_api.domain

import com.cashtrack.cashtrack_api.domain.enum.IncomeType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Income(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    override var label: String = "",
    override var value: Double = 0.0,
    @Enumerated(value = EnumType.STRING)
    var type: IncomeType = IncomeType.GIFT,
    override val dateCreated: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    override var userCashtrack: UserCashtrack? = null,
    override var lastUpdatedAt: LocalDateTime? = null,
    override val source: String = "income"
) : Trackable