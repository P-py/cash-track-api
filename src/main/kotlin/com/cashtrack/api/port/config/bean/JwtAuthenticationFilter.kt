package com.cashtrack.api.port.config.bean

import com.cashtrack.api.application.service.CustomUserDetailsService
import com.cashtrack.api.application.service.TokenService
import com.cashtrack.api.domain.error.exception.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
) : OncePerRequestFilter() {

    @Suppress("SwallowedException", "TooGenericExceptionCaught")
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            val authHeader: String? = request.getHeader("Authorization")
            if (authHeader.doesNotContainBearerToken()) {
                filterChain.doFilter(request, response)
                return
            }
            val jwtToken = authHeader!!.extractTokenValue()
            val email = tokenService.extractEmail(jwtToken)
            if (email != null && SecurityContextHolder.getContext().authentication == null) {
                val foundUser = userDetailsService.loadUserByUsername(email)
                if (tokenService.isValid(jwtToken, foundUser)) {
                    updateContext(foundUser, request)
                }
                filterChain.doFilter(request, response)
            }
        } catch (ex: SignatureException) {
            buildUnauthorizedResponse(
                response,
                "INVALID_TOKEN",
                "Token inválido",
                "A assinatura do token JWT é inválida.",
            )
        } catch (ex: Exception) {
            buildUnauthorizedResponse(
                response,
                "UNKNOWN_ERROR",
                "Erro desconhecido",
                "Ocorreu um erro na autenticação do token.",
            )
        }
    }

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun String?.doesNotContainBearerToken(): Boolean =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue(): String =
        this.substringAfter("Bearer ")

    private fun buildUnauthorizedResponse(
        response: HttpServletResponse,
        code: String,
        message: String,
        description: String,
    ) {
        val errorResponse = ErrorResponse(code, message, description)
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        val mapper = ObjectMapper()
        response.writer.write(mapper.writeValueAsString(errorResponse))
    }
}
