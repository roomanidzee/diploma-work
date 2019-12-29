package com.romanidze.studeeper.modules.user.services

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.UserDTO
import com.romanidze.studeeper.modules.user.services.interfaces.UserService
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier
import java.util.UUID

/**
 * 29.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class UserServiceTest(service: UserService): WordSpec({

    "User Service" should {

        val testDTO = UserDTO(
           id = UUID.randomUUID().toString(),
           username = "testDTO"
        )

        "create user" {

            val testSource = service.save(testDTO)

            StepVerifier.create(testSource)
                    .expectNext(MessageResponseDTO(message="User saved."))
                    .expectComplete()
                    .verify()

        }

        "retrieve all users" {

            val testSource = service.getAllUsers()

            StepVerifier.create(testSource)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify()

        }

    }

})