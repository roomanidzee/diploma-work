package com.romanidze.studeeper.modules.user.mappers

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
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
class ProfileMapperTest(mapper: ProfileMapper): WordSpec({

    "Profile Mapper" should {

        val testString = "test"

        val testProfile = Profile(
           userID = UUID.randomUUID().toString(),
           surname = testString,
           name = testString,
           patronymic = testString,
           email = "${testString}@${testString}.com"
        )

        val testDTO = ProfileDTO(
           surname = testString,
           name = testString,
           patronymic = testString
        )

        "map domain to dto" {

            val result = mapper.domainToDTO(testProfile)

            result.surname shouldBe testProfile.surname

        }

        "map dto to domain" {

            val result = mapper.dtoToDomain(testDTO)

            result.surname shouldBe testDTO.surname

        }

    }

})