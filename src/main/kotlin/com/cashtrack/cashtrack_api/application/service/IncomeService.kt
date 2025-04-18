package com.cashtrack.cashtrack_api.application.service

import com.cashtrack.cashtrack_api.application.extension.extractTokenValue
import com.cashtrack.cashtrack_api.domain.adapter.IncomeAdapter
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeRegisterRequest
import com.cashtrack.cashtrack_api.domain.dto.request.IncomeUpdateRequest
import com.cashtrack.cashtrack_api.domain.dto.response.IncomeResponse
import com.cashtrack.cashtrack_api.domain.error.exception.DatabaseRegisterNotFoundException
import com.cashtrack.cashtrack_api.domain.repository.UserRepository
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import com.cashtrack.cashtrack_api.domain.error.auth.AccessDeniedException
import com.cashtrack.cashtrack_api.domain.error.exception.NotFoundException
import com.cashtrack.cashtrack_api.domain.repository.IncomeRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Suppress("SwallowedException")
class IncomeService(
    private val tokenService: TokenService,
    private val userRepository: UserRepository,
    private val incomeRepository: IncomeRepository,
    private val mapper: IncomeAdapter,
    private val userService: UserService
) {
    @Suppress("ThrowsCount")
    fun getById(id:Long, accessToken:String): IncomeResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val income = incomeRepository.getReferenceById(id)

            if (income.userCashtrack?.id == userId){
                return mapper.mapView(incomeRepository.getReferenceById(id))
            } else {
                throw AccessDeniedException(message = "You don't have permission to access this page.")
            }
        } catch (e: JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    fun getAllByUser(accessToken: String, label: String?): List<IncomeResponse> {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            userService.getUserById(accessToken, userId)
        } catch (e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "There is no user for this id!"))
        }

        val incomesList = if (label.isNullOrEmpty()){
            incomeRepository.findAll()
        } else {
            incomeRepository.getByLabel(label)
        }
        return incomesList
            .filter { i -> i.userCashtrack?.id == userId }
            .toList()
            .map { i -> mapper.mapView(i) }
    }

    fun register(newIncome: IncomeRegisterRequest, accessToken: String): IncomeResponse{
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val new = mapper.mapEntry(newIncome)
        val user = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }

        new.userCashtrack = user
        incomeRepository.save(new)
        return mapper.mapView(new)
    }

    @Suppress("ThrowsCount")
    fun update(updatedIncome: IncomeUpdateRequest, accessToken: String): IncomeResponse{
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val update = incomeRepository.getReferenceById(updatedIncome.id)
            if (update.userCashtrack?.id == userId){
                update.incomeLabel = updatedIncome.incomeLabel
                update.value = updatedIncome.value
                update.type = updatedIncome.type
                update.lastUpdatedAt = LocalDateTime.now()
                return mapper.mapView(update)
            } else throw AccessDeniedException(message = "You don't have permission to access this page.")
        } catch (e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    @Suppress("ThrowsCount")
    fun delete(id:Long, accessToken: String){
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = userRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val deletedIncome = incomeRepository.getReferenceById(id)
            if (deletedIncome.userCashtrack?.id == userId){
                incomeRepository.delete(deletedIncome)
            } else throw AccessDeniedException(message = "You don't have access to this page.")
        } catch (e:JpaObjectRetrievalFailureException){
            throw(NotFoundException(message = "You can't delete an income that does not exist!"))
        }
    }
}
