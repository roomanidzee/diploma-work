package com.romanidze.studeeper.modules.user.services

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

/**
 * 29.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class ProfileServiceTest(service: ProfileService): WordSpec({

    "Profile Service" should {

        val testString = "testDTO"

        val testDTO = ProfileDTO(
           id = testString, 
           surname = testString,
           name = testString,
           patronymic = testString,
           userID = testString,
           email = testString
        )

        "create profile" {

            val testSource = service.save(testDTO)

            StepVerifier.create(testSource)
                    .expectNext(MessageResponseDTO(message="Profile saved."))
                    .expectComplete()
                    .verify()

        }

        "retrieve all profiles" {

            val testSource = service.getAllProfiles()

            StepVerifier.create(testSource)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify()

        }

    }

})