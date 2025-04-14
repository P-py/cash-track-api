package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.IncomeService
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.IncomeResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/incomes")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
class IncomeController(
    private val service: IncomeService,
) {
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader(value = "Authorization") accessToken:String,
        @PathVariable id: Long,
    ): IncomeResponse {}

    @GetMapping
    fun getByUser(
        @RequestHeader(value = "Authorization") accessToken: String,
        @RequestParam(required = false) label:String?,
    ): List<IncomeResponse> {}

    @PostMapping
    @Transactional
    fun register(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid newIncome: IncomeRegisterRequest,
    ) {}

    @PutMapping
    @Transactional
    fun update(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid updatedIncome: IncomeUpdateRequest,
    ): ResponseEntity<IncomeResponse> {}

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(
        @PathVariable id:Long,
        @RequestHeader("Authorization") accessToken: String,
    ) {}
}