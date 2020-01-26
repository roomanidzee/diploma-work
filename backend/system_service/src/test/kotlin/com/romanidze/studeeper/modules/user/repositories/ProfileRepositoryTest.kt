package com.romanidze.studeeper.modules.user.repositories

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileRepository
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
class ProfileRepositoryTest(repo: ProfileRepository): WordSpec({

    "Profile Repository" should {

        val userID = UUID.randomUUID().toString()
        val testString = "test"

        val testProfile = Profile(
           userID = userID,
           surname = testString,
           name = testString,
           patronymic = testString,
           email = "test@test.com"
        )

        "create profile" {

            val testSource = repo.save(testProfile)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.email == testProfile.email
                    }.expectComplete()
                    .verify()

        }

        "update profile" {

            val testNewProfile = Profile(
               id = testProfile.id,
               userID = testProfile.userID,
               surname = "test1",
               name = testProfile.name,
               patronymic = testProfile.patronymic,
               email = testProfile.email
            )

            val testSource = repo.update(testNewProfile)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.modifiedCount == 1L
                    }
                    .expectComplete()
                    .verify()

        }

        "find by id" {

            val testSource = repo.findByID(testProfile.id!!)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.id == testProfile.id
                    }
                    .expectComplete()
                    .verify()

        }

        "find by user"{

            val testSource = repo.findByUser(userID)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.userID == userID
                    }
                    .expectComplete()
                    .verify()

        }

        "delete model" {

            val testSource = repo.delete(testProfile)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.deletedCount == 1L
                    }.expectComplete()
                    .verify()

        }

    }

})