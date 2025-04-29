package com.cashtrack.cashtrack_api.port.controller

import com.cashtrack.cashtrack_api.application.service.IncomeService
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.IncomeResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/incomes")
@CrossOrigin
class IncomeController(
    private val service: IncomeService,
) {
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader(value = "Authorization") accessToken:String,
        @PathVariable id: Long,
    ): ResponseEntity<IncomeResponse> {
        val income = service.getById(id, accessToken)

        return ResponseEntity.ok(income)
    }

    @GetMapping
    fun getByUser(
        @RequestHeader(value = "Authorization") accessToken: String,
        @RequestParam(required = false) label:String?,
    ): ResponseEntity<List<IncomeResponse>> {
        val incomes = service.getAllByUser(accessToken, label)

        return ResponseEntity.ok(incomes)
    }

    @PostMapping
    @Transactional
    fun register(
        @RequestHeader("Authorization") accessToken: String,
        uriBuilder: UriComponentsBuilder,
        @RequestBody @Valid newIncome: IncomeRegisterRequest,
    ): ResponseEntity<IncomeResponse> {
        val createdIncome = service.register(newIncome, accessToken)
        val uri = uriBuilder.path("/incomes/${createdIncome.id}")
            .build()
            .toUri()
        return ResponseEntity.created(uri).body(createdIncome)
    }

    @PutMapping
    @Transactional
    fun update(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid updatedIncome: IncomeUpdateRequest,
    ): ResponseEntity<IncomeResponse> {
        val updateView = service.update(updatedIncome, accessToken)
        return ResponseEntity.ok(updateView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable id:Long,
    ): ResponseEntity<Unit> {
        service.delete(id, accessToken)
        return ResponseEntity.noContent().build()
    }
}