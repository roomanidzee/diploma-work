package com.romanidze.studeeper.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories

@EntityScan(basePackages = [
    "com.romanidze.studeeper.modules.user.domain"
])

@ComponentScan(
        basePackages = [
            "com.romanidze.studeeper.components",
            "com.romanidze.studeeper.config",
            "com.romanidze.studeeper.modules.user"
        ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
