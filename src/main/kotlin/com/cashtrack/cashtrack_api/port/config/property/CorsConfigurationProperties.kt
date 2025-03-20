package com.cashtrack.cashtrack_api.port.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("com.cashtrack.cors")
data class CorsConfigurationProperties(
    val allowedOriginPatterns: String,
)