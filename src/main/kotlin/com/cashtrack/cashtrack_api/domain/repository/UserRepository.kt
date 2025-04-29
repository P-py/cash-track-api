package com.cashtrack.cashtrack_api.domain.repository

import com.cashtrack.cashtrack_api.domain.Trackable
import com.cashtrack.cashtrack_api.domain.UserCashtrack
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository: JpaRepository<UserCashtrack, Long> {
    // Query is auto implemented by JPA Repository
    fun findByEmail(username:String?): Optional<UserCashtrack>

    @Query(
        value = """
            SELECT 'income' AS source, label, value, type, date_created, user_cashtrack_id, last_updated_at
            FROM income
            WHERE user_cashtrack_id = :userId
            
            UNION ALL
            
            SELECT 'expense' AS source, label, value, type, date_created, user_cashtrack_id, last_updated_at
            FROM expense
            WHERE user_cashtrack_id = :userId
            
            order by last_updated_at desc
            
            limit 30;
        """,
        nativeQuery = true
    )
    fun findHistory(@Param("userId") userId: Long): List<Trackable?>
}