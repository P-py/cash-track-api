package com.cashtrack.api.application.extension

fun String.extractTokenValue(): String = this.substringAfter("Bearer ")
