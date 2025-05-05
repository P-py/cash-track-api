package com.cashtrack.api.port.controller

import com.cashtrack.api.application.service.ExpenseService
import com.cashtrack.api.domain.dto.request.ExpenseRegisterRequest
import com.cashtrack.api.domain.dto.request.ExpenseUpdateRequest
import com.cashtrack.api.domain.dto.response.ExpenseResponse
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/expenses")
class ExpenseController(
    private val service: ExpenseService,
) {
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable id: Long,
    ): ResponseEntity<ExpenseResponse> {
        val expense = service.getExpenseById(id, accessToken)

        return ResponseEntity.ok(expense)
    }

    @GetMapping
    fun getByUser(
        @RequestHeader("Authorization") accessToken: String,
        @RequestParam(required = false) label: String?,
    ): ResponseEntity<List<ExpenseResponse>> {
        val expenses = service.getAllByUser(accessToken, label)

        return ResponseEntity.ok(expenses)
    }

    @PostMapping
    @Transactional
    fun register(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid newExpense: ExpenseRegisterRequest,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ExpenseResponse> {
        val expenseView = service.register(newExpense, accessToken)
        val uri = uriBuilder.path("/expenses/${expenseView.id}")
            .build()
            .toUri()
        return ResponseEntity.created(uri).body(expenseView)
    }

    @PutMapping
    @Transactional
    fun update(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid updatedExpense: ExpenseUpdateRequest,
    ): ResponseEntity<ExpenseResponse> {
        val updateView = service.update(updatedExpense, accessToken)
        return ResponseEntity.ok(updateView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable id: Long,
    ): ResponseEntity<Unit> {
        service.delete(id, accessToken)
        return ResponseEntity.noContent().build()
    }
}
