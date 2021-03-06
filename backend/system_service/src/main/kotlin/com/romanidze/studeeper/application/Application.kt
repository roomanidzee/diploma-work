package com.romanidze.studeeper.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

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
            "com.romanidze.studeeper.modules.graphods",
            "com.romanidze.studeeper.modules.employer",
            "com.romanidze.studeeper.modules.files",
            "com.romanidze.studeeper.modules.email",
            "com.romanidze.studeeper.modules.worker",
            "com.romanidze.studeeper.modules.exceptions",
            "com.romanidze.studeeper.modules.facility"
        ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
