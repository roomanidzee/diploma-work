package com.romanidze.studeeper.modules.graphods.mappers

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

/**
 * 29.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class GraphRecordMapperTest(mapper: GraphRecordMapper): WordSpec({

    "GraphRecord Mapper" should {

        val testString = "test"
        val currentTime = LocalDateTime.now()

        val testRecord = GraphRecord(
            specialization = testString,
            graduation = currentTime,
            profileID = testString
        )

        val testDTO = GraphRecordDTO(
            specialization = testString,
            graduation = currentTime,
            profileID = testString
        )

        "map domain to dto" {

            val result = mapper.domainToDTO(testRecord)

            result.specialization shouldBe testRecord.specialization

        }

        "map dto to domain" {

            val result = mapper.dtoToDomain(testDTO)

            result.specialization shouldBe testDTO.specialization

        }

    }

})