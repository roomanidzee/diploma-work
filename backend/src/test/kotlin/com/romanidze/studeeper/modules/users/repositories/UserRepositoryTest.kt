package com.romanidze.studeeper.modules.users.repositories

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
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

        "create user" {

            val testUser = User(
                    username="test",
                    password="test"
            )

            val testSaveSource: Mono<User> = repo.save(testUser)

            StepVerifier.create(testSaveSource)
                    .expectNextMatches {
                        it.username == "test" && it.password == "test"
                    }.expectComplete()
                    .verify()
            

        }

     }

})