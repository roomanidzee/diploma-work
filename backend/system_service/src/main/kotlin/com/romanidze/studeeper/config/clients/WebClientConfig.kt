package com.romanidze.studeeper.config.clients

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.netty.tcp.TcpClient

/**
 * 13.02.2020
 *
 * Configuration for web clients in system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
class WebClientConfig {

    @Bean
    fun tcpClient(): TcpClient =
            TcpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                    .doOnConnected {
                        it.addHandlerLast(ReadTimeoutHandler(2))
                                .addHandlerLast(WriteTimeoutHandler(2))
                    }

}