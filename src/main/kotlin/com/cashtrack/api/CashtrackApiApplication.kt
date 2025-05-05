package com.cashtrack.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CashtrackApiApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<CashtrackApiApplication>(*args)
}
