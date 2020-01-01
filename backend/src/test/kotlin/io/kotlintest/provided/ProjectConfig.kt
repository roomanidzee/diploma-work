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

    private val execCommand1 = "docker-compose -f docker/docker-compose.yml up -d mongo"
    private val execCommand2 = "docker-compose -f docker/docker-compose.yml down -v"

    override fun beforeAll() {
        super.beforeAll()
        Runtime.getRuntime().exec(execCommand1)
    }

    override fun afterAll() {
        super.afterAll()
        Runtime.getRuntime().exec(execCommand2)
    }

    override fun extensions(): List<ProjectLevelExtension> = listOf(
            SpringAutowireConstructorExtension
    )
}