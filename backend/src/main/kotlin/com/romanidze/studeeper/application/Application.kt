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
            "com.romanidze.studeeper.modules.security"
        ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
