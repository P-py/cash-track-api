package com.cashtrack.cashtrack_api.domain.adapter

interface Adapter<C, V, E>{
    // C -> class
    // V -> view
    // E -> entry
    fun mapView(c:C):V
    fun mapEntry(e:E):C
}