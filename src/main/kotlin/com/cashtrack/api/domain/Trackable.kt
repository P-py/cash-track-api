package com.cashtrack.api.domain

import java.time.LocalDateTime

interface Trackable {
    var id: Long?
    var label: String
    var value: Double
    val dateCreated: LocalDateTime
    var userCashtrack: UserCashtrack?
    var lastUpdatedAt: LocalDateTime?
    val source: String
}
