package com.example.testing

interface ApiClient {
    fun sendData(path: String, data: ByteArray)
}