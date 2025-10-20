package com.example.testing

class PanelViewModel(private val repository: TemperatureRepository) {

    fun loadTemperature() {
        val currentTemp = repository.fetchCurrentTemperature()
        println("Temperature loaded: $currentTemp")
    }
}

class UserViewModel(private val repository: UserRepository) {
    suspend fun loadUser(id: String) {
        val user = repository.getUserProfile(id)
        // ... какая-то логика с пользователем
    }
}

class DataViewModel(private val apiClient: ApiClient) {
    suspend fun loadUser(id: String) {
        apiClient.sendData(id, ByteArray(0))
        // ... какая-то логика с пользователем
    }
}