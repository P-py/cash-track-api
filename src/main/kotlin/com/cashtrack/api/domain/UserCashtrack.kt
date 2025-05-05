package com.cashtrack.api.domain

import com.cashtrack.api.domain.enum.Role
import jakarta.persistence.*

@Entity
@Table(name = "user_cashtrack")
data class UserCashtrack(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String = "",
    var email: String = "",
    val password: String = "",
    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.USER,
    @OneToMany(mappedBy = "userCashtrack", cascade = [CascadeType.ALL], orphanRemoval = true)
    var expenseList: List<Expense> = ArrayList(),
    @OneToMany(mappedBy = "userCashtrack", cascade = [CascadeType.ALL], orphanRemoval = true)
    var incomeList: List<Income> = ArrayList(),
)
