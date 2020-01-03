package com.romanidze.studeeper.config.mongo

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 * 27.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableReactiveMongoRepositories(
        basePackages = [
            "com.romanidze.studeeper.modules.user.repositories"
        ]
)
@EntityScan(
        basePackages = [
            "com.romanidze.studeeper.modules.user.domain"
        ]
)
class MongoDBConfig