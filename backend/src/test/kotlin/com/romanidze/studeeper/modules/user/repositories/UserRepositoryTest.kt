package com.romanidze.studeeper.modules.user.repositories

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

/**
 * 28.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class UserRepositoryTest(repo: UserRepository): WordSpec({

     "UserRepository" should {

         val testUser = User(
           username="test",
           password="test"
         )

        "create user" {

            val testSaveSource: Mono<User> = repo.save(testUser)

            StepVerifier.create(testSaveSource)
                    .expectNextMatches {
                        it.username == "test" && it.password == "test"
                    }.expectComplete()
                    .verify()
            

        }

        "retrieve all users" {

            val testSelectSource: Flux<User> = repo.findAll()

            StepVerifier.create(testSelectSource)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify()

        }

        "update user" {

            val newUser = User(
              id=testUser.id,
              username="test1",
              password=testUser.password
            )

            val testUpdateSource = repo.update(newUser)

            StepVerifier.create(testUpdateSource)
                    .expectNextMatches {
                        it.id == newUser.id
                    }
                    .expectComplete()
                    .verify()

        }

        "find by username" {

            val testSource = repo.findByUsername("test1")

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.username == "test1"
                    }
                    .expectComplete()
                    .verify()

        }

        "find by id" {

            val testSource = repo.findByID(testUser.id!!)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.id == testUser.id!!
                    }
                    .expectComplete()
                    .verify()


        }

        "delete model" {

            val testSource = repo.delete(testUser)

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.deletedCount == 1L
                    }
                    .expectComplete()
                    .verify()

        }

     }

})