package com.romanidze.studeeper.modules.facility.analysis.client

import org.springframework.context.annotation.Bean
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient

/**
 * 10.05.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class AnalysisServiceClientConfig(
        private val props: AnalysisServiceProperties,
        private val tcpClient: TcpClient
) {

    @Bean
    fun analysisServiceClient(): WebClient {

        return WebClient.builder()
                .baseUrl("http://${props.host}/${props.port}")
                .clientConnector(
                        ReactorClientHttpConnector(HttpClient.from(tcpClient))
                )
                .build()

    }

}