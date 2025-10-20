package com.example.testing

interface TemperatureRepository {
    fun fetchCurrentTemperature(): Int
}

interface UserRepository {
    suspend fun getUserProfile(id: String): User
}