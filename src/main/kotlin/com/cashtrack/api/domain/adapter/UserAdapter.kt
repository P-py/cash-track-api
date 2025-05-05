package com.cashtrack.api.domain.adapter

import com.cashtrack.api.domain.UserCashtrack
import com.cashtrack.api.domain.dto.request.UserRegisterRequest
import com.cashtrack.api.domain.dto.response.UserResponse
import com.cashtrack.api.domain.enum.Role
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val encoder: PasswordEncoder,
) : Adapter<UserCashtrack, UserResponse, UserRegisterRequest> {

    override fun mapView(c: UserCashtrack): UserResponse {
        return UserResponse(
            id = c.id,
            username = c.username,
            email = c.email,
        )
    }

    override fun mapEntry(e: UserRegisterRequest): UserCashtrack {
        return UserCashtrack(
            username = e.username,
            email = e.email,
            role = Role.USER,
            password = encoder.encode(e.password),
        )
    }
}
