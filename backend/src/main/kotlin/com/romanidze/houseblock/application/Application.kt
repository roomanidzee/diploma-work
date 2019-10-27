package com.romanidze.houseblock.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
        basePackages = [
            "com.romanidze.houseblock.components",
            "com.romanidze.houseblock.config",
            "com.romanidze.houseblock.modules.user"
        ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
