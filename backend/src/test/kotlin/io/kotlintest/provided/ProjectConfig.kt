package io.kotlintest.provided

import io.kotlintest.AbstractProjectConfig
import io.kotlintest.extensions.ProjectLevelExtension
import io.kotlintest.spring.SpringAutowireConstructorExtension

/**
 * 28.11.2019
 *
 * Config for tests of project
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
object ProjectConfig: AbstractProjectConfig() {

    private val startCommands = mutableListOf(
       "docker-compose -f docker/docker-compose.yml up -d mongo neo4j"
    )

    private val stopCommands = mutableListOf(
       "docker-compose -f docker/docker-compose.yml down -v",
       "docker container rm std_mongo std_neo4j"
    )

    override fun beforeAll() {
        super.beforeAll()

        startCommands.forEach {
            Runtime.getRuntime().exec(it)
        }

        Thread.sleep(20_000)
    }

    override fun afterAll() {
        super.afterAll()

        stopCommands.forEach {
            Runtime.getRuntime().exec(it)
        }

    }

    override fun extensions(): List<ProjectLevelExtension> = listOf(
            SpringAutowireConstructorExtension
    )
}