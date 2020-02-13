package com.romanidze.studeeper.modules.email.client

import org.springframework.context.annotation.Bean
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class SendGridMailClientConfig(
    private val props: SendGridMailProperties,
    private val tcpClient: TcpClient
) {

    @Bean
    fun sendGridClient(): WebClient{

        return WebClient.builder()
                        .baseUrl(this.props.baseURL)
                        .defaultHeader("Authorization", "Bearer ${this.props.apiKey}")
                        .defaultHeader("Content-Type", "application/json")
                        .clientConnector(
                            ReactorClientHttpConnector(HttpClient.from(tcpClient))
                        )
                        .build()

    }

}