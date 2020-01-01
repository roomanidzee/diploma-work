package com.romanidze.studeeper.modules.security.routes

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.security.dto.LoginDTO
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.http.MediaType
import com.romanidze.studeeper.modules.security.dto.RegistrationDTO
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

/**
 * 01.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
@AutoConfigureWebTestClient
class SecurityRoutesTest(client: WebTestClient): WordSpec({

    val testUser = RegistrationDTO(
            username = "test_sec",
            password = "test_sec"
    )

    "Registration routes" should {

        "register user"{

            client.post()
                  .uri("/api/security/register")
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(BodyInserters.fromValue(testUser))
                  .exchange()
                  .expectStatus().isOk

        }

    }

    "Authentication routes" should {

        "authenticate user" {

            client.post()
                  .uri("/api/security/login")
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(BodyInserters.fromValue(LoginDTO(
                          testUser.username, testUser.password
                  )))
                  .exchange()
                  .expectStatus().isOk

        }

    }

})