package com.romanidze.studeeper.modules.files.client

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class FileServiceClientConfig(private val props: FileServiceProperties) {

    @Bean
    fun fileServiceClient(): WebClient{

        val tcpClient = TcpClient.create()
                                 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                                 .doOnConnected {
                                    it.addHandlerLast(ReadTimeoutHandler(2))
                                      .addHandlerLast(WriteTimeoutHandler(2))
                                 }

        return WebClient.builder()
                .baseUrl("http://${props.host}/${props.port}")
                .clientConnector(
                    ReactorClientHttpConnector(HttpClient.from(tcpClient))
                )
                .build()

    }

}