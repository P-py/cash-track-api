package com.cashtrack.api.application.service

import com.cashtrack.api.application.extension.extractTokenValue
import com.cashtrack.api.domain.adapter.HistoryAdapter
import com.cashtrack.api.domain.adapter.UserAdapter
import com.cashtrack.api.domain.dto.request.UserRegisterRequest
import com.cashtrack.api.domain.dto.request.UserUpdateRequest
import com.cashtrack.api.domain.dto.response.BalanceResponse
import com.cashtrack.api.domain.dto.response.HistoryResponse
import com.cashtrack.api.domain.dto.response.UserResponse
import com.cashtrack.api.domain.error.auth.AccessDeniedException
import com.cashtrack.api.domain.error.enum.ExceptionEnum
import com.cashtrack.api.domain.error.exception.CustomException
import com.cashtrack.api.domain.error.exception.DatabaseRegisterNotFoundException
import com.cashtrack.api.domain.error.exception.NotFoundException
import com.cashtrack.api.domain.error.exception.UserAlreadyExistsException
import com.cashtrack.api.domain.repository.UserRepository
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service

@Service
@Suppress("SwallowedException")
class UserService(
    private val usersRepository: UserRepository,
    private val mapper: UserAdapter,
    private val tokenService: TokenService,
    private val historyMapper: HistoryAdapter,
) {
    fun registerNewUser(newUser: UserRegisterRequest): UserResponse {
        val new = mapper.mapEntry(newUser)

        val userExists = usersRepository.findByEmail(new.email).isPresent

        if (!userExists) {
            usersRepository.save(new)
            return mapper.mapView(new)
        } else {
            throw UserAlreadyExistsException(
                message = "An user for this e-mail already exists.",
            )
        }
    }

    fun getAccountById(accessToken: String, userId: Long?): UserResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val id = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            return mapper.mapView(usersRepository.getReferenceById(userId ?: id))
        } catch (ex: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    fun getBalance(accessToken: String): BalanceResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val userIncomeList = usersRepository.getReferenceById(userId)
                .incomeList
                .sumOf { i -> i.value }
            val userExpenseList = usersRepository.getReferenceById(userId)
                .expenseList
                .sumOf { i -> i.value }
            return BalanceResponse(
                totalIncomes = userIncomeList,
                totalExpenses = userExpenseList,
                balance = userIncomeList - userExpenseList,
            )
        } catch (ex: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    fun getSpendingHistory(accessToken: String): List<HistoryResponse?> {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val userHistory = usersRepository.findHistory(userId)
            val response = userHistory.mapNotNull {
                historyMapper.mapView(it!!)
            }
            return response
        } catch (ex: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    fun getUserById(accessToken: String, userId: Long?): UserResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val id = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            return mapper.mapView(usersRepository.getReferenceById(userId ?: id))
        } catch (e: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    @Suppress("ThrowsCount", "NestedBlockDepth")
    fun updateUserData(updatedUser: UserUpdateRequest, accessToken: String): UserResponse {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val toUpdate = usersRepository.getReferenceById(userId)
            updatedUser.username?.let {
                if (updatedUser.username != toUpdate.username) {
                    toUpdate.username = updatedUser.username
                } else {
                    throw CustomException(
                        errorEnum = ExceptionEnum.USERNAME_CANNOT_BE_EQUAL,
                    )
                }
            }
            updatedUser.email?.let {
                if (updatedUser.email != toUpdate.email) {
                    val userExists = usersRepository.findByEmail(updatedUser.email).isPresent

                    if (userExists) {
                        throw UserAlreadyExistsException(
                            message = "A user for this e-mail already exists.",
                        )
                    }
                    toUpdate.email = updatedUser.email
                } else {
                    throw CustomException(
                        errorEnum = ExceptionEnum.EMAIL_CANNOT_BE_EQUAL,
                    )
                }
            }
            return mapper.mapView(toUpdate)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "Oh, something went wrong!! User not found!"))
        }
    }

    fun deleteUserAccount(accessToken: String) {
        val tokenValue = accessToken.extractTokenValue()
        val userEmail = tokenService.extractEmail(tokenValue)

        val userId = usersRepository.findByEmail(userEmail)
            .orElseThrow { DatabaseRegisterNotFoundException(message = "Oh, something went wrong!! User not found!") }
            .id ?: throw AccessDeniedException(message = "You don't have permission to access this page.")

        try {
            val deletedUser = usersRepository.getReferenceById(userId)
            usersRepository.delete(deletedUser)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw(NotFoundException(message = "You can't delete a user that does not exist!"))
        }
    }
}
