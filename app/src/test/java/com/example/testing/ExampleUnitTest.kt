package com.example.testing

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `должен быть вызван метод и репозитория`() {
        val mockRepository = mockk<TemperatureRepository>()
        every { mockRepository.fetchCurrentTemperature() } returns 25

        val viewModel = PanelViewModel(mockRepository)

        viewModel.loadTemperature()

        verify(exactly = 1) { mockRepository.fetchCurrentTemperature() }
    }

    @Test
    fun `тестируем юзера`(){
        return runTest {
            val mockRepostiroy = mockk<UserRepository>()
            val fakeUser = User("1")
            coEvery { mockRepostiroy.getUserProfile("1") } returns fakeUser

            val viewModel = UserViewModel(mockRepostiroy)

            viewModel.loadUser("1")

            coVerify { mockRepostiroy.getUserProfile("1") }
        }
    }

    @Test
    fun `sendData должен вызываться с правильным путем`() {
        // Arrange
        val mockApi = mockk<ApiClient>(relaxed = true) // relaxed = true, чтобы не мокать возвращаемое значение
        every { mockApi.sendData(any(), any()) } returns Unit // Базовая настройка

        // Act
        // Какой-то код, который вызывает mockApi.sendData("/users/profile", someBytes)

        // Assert/Verify
        // Проверяем, что метод был вызван с ЛЮБЫМ массивом байт, но с КОНКРЕТНЫМ путем
        verify { mockApi.sendData(path = eq("/users/profile"), data = any()) }
    }

    @Test
    fun `sadf`(){
        val mockRepo = mockk<UserEmailRepository>(relaxed = true)
        val userSlot = slot<UserEmail>()
        every { mockRepo.saveUser(capture(userSlot)) } returns Unit
        val regService = RegService(mockRepo)

        val user = UserEmail("a", "b")
        regService.registerUser(user)

        val capturedUser = userSlot.captured
        assertEquals("a", capturedUser.email)
        assertEquals("b", capturedUser.password)
        assertEquals("pending", capturedUser.status)
    }
}