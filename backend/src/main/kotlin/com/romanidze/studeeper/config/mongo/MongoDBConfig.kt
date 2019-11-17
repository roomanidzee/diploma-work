package com.romanidze.studeeper.config.mongo

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 *
 * Interface with config for MongoDB
 *
 * 17.11.2019
 * @author Andrey Romanov
 *
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = [
    "com.romanidze.studeeper.modules.user.repositories"
])
@EntityScan(basePackages = [
    "com.romanidze.studeeper.modules.user.domain"
])
interface MongoDBConfig