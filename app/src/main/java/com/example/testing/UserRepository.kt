package com.example.testing

interface UserEmailRepository {
    fun saveUser(userEmail: UserEmail)
}

class RegService(val userRepository: UserEmailRepository){
    fun registerUser(userEmail: UserEmail){
        userRepository.saveUser(userEmail)
    }
}