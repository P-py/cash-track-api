package com.cashtrack.api.port.config.bean

import com.cashtrack.api.port.config.property.CorsConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CorsConfigurationProperties::class)
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
    private val corsProperties: CorsConfigurationProperties,
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
    ): SecurityFilterChain {
        val cors = Customizer<CorsConfigurer<HttpSecurity>> { it.configurationSource(corsConfig()) }

        val config = http
            .cors(cors)
            .csrf { it.disable() }
            .authorizeHttpRequests(authorizedRequests())
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

        return config
    }

    @Bean
    fun corsConfig(): CorsConfigurationSource {
        val allowedOrigins = corsProperties.allowedOriginPatterns.split(";").map { it.trim() }
        val allowedMethods = listOf(
            HttpMethod.OPTIONS.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.PUT.name(),
            HttpMethod.GET.name(),
            HttpMethod.HEAD.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.POST.name(),
        )

        val configuration = CorsConfiguration()
        configuration.addAllowedHeader("*")
        configuration.addExposedHeader("Authorization")
        configuration.allowedOriginPatterns = allowedOrigins
        configuration.allowedMethods = allowedMethods
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }

    @Suppress("MaxLineLength")
    private fun authorizedRequests(): Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
        return Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
            it
                .requestMatchers(
                    "/auth",
                    "/error",
                    "/hello",
                )
                .permitAll()
                .requestMatchers(
                    HttpMethod.GET,
                    "/users",
                    "/expenses/admin-list",
                    "/incomes/admin-list",
                    "/swagger-ui/**",
                    "/swagger-ui/index.html",
                    "/v3/api-docs/**",
                )
                .hasRole("ADMIN")
                .requestMatchers(
                    HttpMethod.GET,
                    "/users/account",
                    "/users/history",
                    "/users/balance",
                    "/incomes/{id}",
                    "/incomes",
                    "/expenses/{id}",
                    "/expenses",
                )
                .fullyAuthenticated()
                .requestMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/incomes", "/expenses")
                .fullyAuthenticated()
                .requestMatchers(HttpMethod.PUT, "/users", "/incomes", "/expenses")
                .fullyAuthenticated()
                .requestMatchers(
                    HttpMethod.DELETE,
                    "/users/delete-my-account",
                    "/incomes/{id}",
                    "/expenses/{id}",
                )
                .fullyAuthenticated()
        }
    }
}
