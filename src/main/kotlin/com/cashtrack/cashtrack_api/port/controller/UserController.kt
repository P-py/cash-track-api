package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.UserService
import com.cashtrack.cashtrack_api.domain.dto.request.UserRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.UserUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.BalanceResponse
import com.cashtrack.cashtrack_api.domain.dto.response.UserResponse
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@CrossOrigin
@RequestMapping("/users")
class UserController(
    private val service: UserService,
) {
    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid newUser: UserRegisterRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserResponse> {
        val userResponse = service.registerNewUser(newUser)
        val uri = uriBuilder.path("/users/${userResponse.id}")
            .build()
            .toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }

    @GetMapping("/account")
    fun getAccountById(
        @RequestHeader(value = "Authorization") accessToken:String
    ): ResponseEntity<UserResponse> {
        val accountDetails = service.getAccountById(accessToken, null)
        return ResponseEntity.ok(accountDetails)
    }

    @GetMapping("/balance")
    fun getBalanceById(
        @RequestHeader(value = "Authorization") accessToken:String
    ): ResponseEntity<BalanceResponse> {
        val userBalance = service.getBalance(accessToken)
        return ResponseEntity.ok(userBalance)
    }

    @PutMapping
    @Transactional
    fun update(
        @RequestBody @Valid updatedUser: UserUpdateRequest,
        @RequestHeader("Authorization") accessToken: String
    ):ResponseEntity<UserResponse>{
        val updateView = service.updateUserData(updatedUser, accessToken)
        return ResponseEntity.ok(updateView)
    }

    @DeleteMapping("/delete-my-account")
    @Transactional
    fun delete(
        @RequestHeader("Authorization") accessToken: String
    ): ResponseEntity<Unit>{
        service.deleteUserAccount(accessToken)
        return ResponseEntity.noContent().build()
    }
}