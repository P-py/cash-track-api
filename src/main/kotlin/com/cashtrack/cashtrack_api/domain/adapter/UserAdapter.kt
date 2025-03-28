package com.cashtrack.cashtrack_api.domain.adapter

import com.cashtrack.cashtrack_api.domain.UserCashtrack
import com.cashtrack.cashtrack_api.domain.auth.request.UserRegisterRequest
import com.cashtrack.cashtrack_api.domain.auth.response.UserResponse
import com.cashtrack.cashtrack_api.domain.enum.Role
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val encoder: PasswordEncoder
): Adapter<UserCashtrack, UserResponse, UserRegisterRequest> {

    override fun mapView(c:UserCashtrack): UserResponse {
        return UserResponse(
            id = c.id,
            username = c.username
        )
    }

    override fun mapEntry(e: UserRegisterRequest):UserCashtrack {
        return UserCashtrack(
            username = e.username,
            email = e.email,
            role = Role.USER,
            password = encoder.encode(e.password)
        )
    }
}