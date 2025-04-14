package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.ExpenseService
import com.cashtrack.cashtrack_api.domain.dto.request.ExpenseRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.ExpenseUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.ExpenseResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/expenses")
@SecurityRequirement(name = "bearerAuth")
class ExpenseController(
    private val service: ExpenseService,
) {
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable id:Long,
    ): ExpenseResponse {}

    @GetMapping
    fun getByUser(
        @RequestHeader("Authorization") accessToken: String,
        @RequestParam(required = false) label:String?,
    ): List<ExpenseResponse> {}

    @PostMapping
    @Transactional
    fun register(
        @RequestHeader("Authorization") accessToken:String,
        @RequestBody @Valid newExpense: ExpenseRegisterRequest,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ExpenseResponse> {}

    @PutMapping
    @Transactional
    fun update(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid updatedExpense: ExpenseUpdateRequest,
    ): ResponseEntity<ExpenseResponse> {}

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable id:Long,
    ) {}
}