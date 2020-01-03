package com.romanidze.studeeper.config.clickhouse

import cc.blynk.clickhouse.ClickHouseDataSource
import cc.blynk.clickhouse.settings.ClickHouseProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors
import javax.sql.DataSource

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
class ClickHouseConfig(private val props: ClickHouseJDBCProperties) {

    @Bean
    fun scheduler(): Scheduler{
        return Schedulers.elastic()
    }

    @Bean
    fun jdbcScheduler(): Scheduler{
        return Schedulers.fromExecutor(
            Executors.newFixedThreadPool(this.props.connectionPoolSize)
        )
    }

    @Bean
    fun chProperties(): ClickHouseProperties {

        val chProps = ClickHouseProperties()

        chProps.host = this.props.host
        chProps.port = this.props.port
        chProps.database = this.props.database
        chProps.user = this.props.user
        chProps.password = this.props.password

        return chProps

    }

    @Bean
    @Primary
    fun dataSource(): DataSource {
        return ClickHouseDataSource(this.props.getDatabaseURL(), chProperties())
    }

    @Bean
    @Primary
    fun chJdbcTemplate(): JdbcTemplate{
        return JdbcTemplate(dataSource())
    }

}