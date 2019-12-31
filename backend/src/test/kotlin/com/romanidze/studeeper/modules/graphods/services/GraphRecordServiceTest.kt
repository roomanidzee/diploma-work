package com.romanidze.studeeper.modules.graphods.services

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.repositories.GraphRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier
import java.time.LocalDateTime

/**
 * 29.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class GraphRecordServiceTest(
        repo: GraphRecordRepository,
        service: GraphRecordService
): WordSpec({

    "GraphRecord Service" should {

        val currentTime = LocalDateTime.now()

        val testRecord = GraphRecord(
           specialization = "test",
           graduation = currentTime,
           profileID = "test"
        )

        val expectedResult = repo.save(testRecord)

        "aggregate records by specialization" {

            val testSource = service.getRecordsBySpecialization("test")

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.specialization == expectedResult.specialization
                    }
                    .expectComplete()
                    .verify()

        }

        "aggregate records by specialization and time" {

            val testSource = service.getRecordsBySpecializationAndGraduate(
                    "test",
                    currentTime
            )

            StepVerifier.create(testSource)
                    .expectNextMatches {
                        it.specialization == expectedResult.specialization
                    }
                    .expectComplete()
                    .verify()

        }

    }

})