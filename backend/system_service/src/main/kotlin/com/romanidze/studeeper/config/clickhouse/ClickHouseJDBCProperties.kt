package com.romanidze.studeeper.config.clickhouse

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("spring.data.clickhouse")
class ClickHouseJDBCProperties {

    lateinit var host: String
    var port: Int = 8123
    lateinit var database: String
    lateinit var user: String
    lateinit var password: String
    var connectionPoolSize: Int = 10

    fun getDatabaseURL(): String = "jdbc://clickhouse://${host}:${port}/${database}"

}