package com.romanidze.studeeper.config.neo4j

import org.neo4j.springframework.data.repository.config.EnableReactiveNeo4jRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

/**
 * 27.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableReactiveNeo4jRepositories(
        basePackages = [
            "com.romanidze.studeeper.modules.graphods.repositories"
        ]
)
@EntityScan(
        basePackages = [
            "com.romanidze.studeeper.modules.graphods.domain"
        ]
)
class Neo4JConfig