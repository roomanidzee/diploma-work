package com.romanidze.studeeper.modules.security.authentication

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest

/**
 * 01.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class JWTAuthenticationTest: WordSpec({

    "JWTAuthentication" should {

        val testAuthentication = JWTAuthentication("test")

        "retrieve token" {
            testAuthentication.name shouldBe "test"
        }

        "check for null values" {
            testAuthentication.credentials shouldBe null
            testAuthentication.principal shouldBe null
            testAuthentication.details shouldBe null
        }

        "check if authenticated" {
            testAuthentication.isAuthenticated shouldBe false
        }

    }

})
