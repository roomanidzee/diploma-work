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

    override fun extensions(): List<ProjectLevelExtension> = listOf(
        SpringAutowireConstructorExtension
    )
}