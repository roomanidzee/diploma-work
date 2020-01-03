package com.romanidze.studeeper.modules.user.mappers

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.dto.UserDTO
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

/**
 * 29.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class UserMapperTest(mapper: UserMapper): WordSpec({

    "User Mapper" should {

        val testUser = User(
          username="test",
          password="test"
        )

        val testDTO = UserDTO(
          id = UUID.randomUUID().toString(),
          username = "test"
        )

        "map domain to dto" {

            val result = mapper.domainToDTO(testUser)

            result.username shouldBe testUser.username
            result.id shouldBe testUser.id

        }

        "map dto to domain" {

            val result = mapper.dtoToDomain(testDTO)

            result.username shouldBe testDTO.username
            result.id shouldBe testDTO.id

        }

    }


})