package com.cashtrack.cashtrack_api.application.service

import com.cashtrack.cashtrack_api.application.extension.extractTokenValue
import com.cashtrack.cashtrack_api.domain.adapter.ExpenseAdapter
import com.cashtrack.cashtrack_api.domain.dto.request.ExpenseRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.ExpenseUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.ExpenseResponse
import com.cashtrack.cashtrack_api.domain.error.exception.DatabaseRegisterNotFoundException
import com.cashtrack.cashtrack_api.domain.error.auth.AccessDeniedException
import com.cashtrack.cashtrack_api.domain.error.exception.NotFoundException
import com.cashtrack.cashtrack_api.domain.repository.ExpenseRepository
import com.cashtrack.cashtrack_api.domain.repository.UserRepository
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ExpenseService(
    private val tokenService: TokenService,
    private val userRepository: UserRepository,
    private val expenseRepository: ExpenseRepository,
    private val mapper: ExpenseAdapter,
    private val userService: UserService,
) {
    @Suppress("SwallowedException", "ThrowsCount")
    fun getExpenseById(
        id:Long,
        accessToken:String,
    ): ExpenseResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val expense = expenseRepository.getReferenceById(id)
            if (expense.userCashtrack!!.id == userId){
                return mapper.mapView(expenseRepository.getReferenceById(id))
            } else {
                throw AccessDeniedException(message = "You don't have permission to access this page.")
            }
        } catch (e: JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    @Suppress("SwallowedException")
    fun getAllByUser(accessToken:String, label:String?): List<ExpenseResponse> {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            userService.getUserById(accessToken, userId)
        } catch(e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "There is no user for this id!"))
        }
        val expensesList = if (label.isNullOrBlank()){
            expenseRepository.findAll()
        } else {
            expenseRepository.getByLabel(label)
        }
        return expensesList
            .filter { e -> e.userCashtrack!!.id == userId }
            .toList()
            .map{ e -> mapper.mapView(e) }
    }

    fun register(newExpense: ExpenseRegisterRequest, accessToken: String): ExpenseResponse{
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val newEntry = mapper.mapEntry(newExpense)
        val user = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }

        newEntry.userCashtrack = user
        expenseRepository.save(newEntry)
        return mapper.mapView(newEntry)
    }

    @Suppress("SwallowedException", "ThrowsCount")
    fun update(updatedExpense: ExpenseUpdateRequest, accessToken: String): ExpenseResponse{
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val update = expenseRepository.getReferenceById(updatedExpense.id)
            if (update.userCashtrack!!.id == userId) {
                update.label = updatedExpense.expenseLabel
                update.value = updatedExpense.value
                update.type = updatedExpense.type
                update.lastUpdatedAt = LocalDateTime.now()
                return mapper.mapView(update)
            } else throw AccessDeniedException(message =  "You don't have permission to access this page.")
        } catch (e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    @Suppress("SwallowedException", "ThrowsCount")
    fun delete(id:Long, accessToken: String) {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val deletedExpense = expenseRepository.getReferenceById(id)
            if (deletedExpense.userCashtrack!!.id == userId){
                expenseRepository.delete(deletedExpense)
            } else throw AccessDeniedException(message = "You don't have access to this page.")
        } catch (e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "You can't delete a user that does not exist!"))
        }
    }
}