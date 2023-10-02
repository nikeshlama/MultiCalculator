package com.example.multicalculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform