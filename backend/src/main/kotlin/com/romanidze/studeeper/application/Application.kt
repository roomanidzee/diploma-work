package com.romanidze.studeeper.application

import org.neo4j.springframework.data.repository.config.EnableReactiveNeo4jRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 * Main class for all system
 *
 * 17.11.2019
 * @author Andrey Romanov
 *
 */
@SpringBootApplication
@ComponentScan(
        basePackages = [
            "com.romanidze.studeeper.config",
            "com.romanidze.studeeper.modules.user",
            "com.romanidze.studeeper.modules.security",
            "com.romanidze.studeeper.modules.graphods"
        ]
)
@EnableReactiveMongoRepositories(
        basePackages = [
            "com.romanidze.studeeper.modules.user.repositories"
        ]
)
@EntityScan(
        basePackages = [
            "com.romanidze.studeeper.modules.user.domain",
            "com.romanidze.studeeper.modules.graphods.domain"
        ]
)
@EnableReactiveNeo4jRepositories(
        basePackages = [
            "com.romanidze.studeeper.modules.graphods.repositories"
        ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
