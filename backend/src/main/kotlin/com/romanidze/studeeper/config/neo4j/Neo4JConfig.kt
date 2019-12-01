package com.romanidze.studeeper.config.neo4j

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

/**
 * 01.12.2019
 *
 * Interface for config with Neo4J
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableNeo4jRepositories(basePackages = [
    "com.romanidze.studeeper.modules.graphods.repositories"
])
@EntityScan(basePackages = [
    "com.romanidze.studeeper.modules.graphods.domain"
])
interface Neo4JConfig