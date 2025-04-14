package com.cashtrack.cashtrack_api.application.extension

fun String.extractTokenValue():String = this.substringAfter("Bearer ")