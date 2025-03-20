package com.cashtrack.cashtrack_api.domain.repository

import com.cashtrack.cashtrack_api.domain.UserCashtrack
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserCashtrack, Long> {
    // Query is auto implemented by JPA Repository
    fun findByEmail(username:String?): UserCashtrack?
}