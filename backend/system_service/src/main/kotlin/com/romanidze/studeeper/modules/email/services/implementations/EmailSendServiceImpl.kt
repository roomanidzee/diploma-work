package com.romanidze.studeeper.modules.email.services.implementations

import com.romanidze.studeeper.modules.email.services.interfaces.EmailSendService
import com.romanidze.studeeper.modules.email.dto.MailDTO
import com.romanidze.studeeper.modules.email.dto.SendGridMailDTO
import com.romanidze.studeeper.modules.email.client.SendGridMailProperties

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.BodyInserters

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class EmailSendServiceImpl(
    private val props: SendGridMailProperties,
    private val sendGridClient: WebClient
): EmailSendService {

    override fun sendMail(mailObj: MailDTO): Flux<Unit> {

        val sendGridObj = SendGridMailDTO(mailObj)
        val requestBody = BodyInserters.fromValue(sendGridObj)

        return this.sendGridClient.post()
                                .uri(this.props.mailEndpoint)
                                .body(requestBody)
                                .retrieve()
                                .onStatus(
                                    {t: HttpStatus -> t.is4xxClientError},
                                    { Mono.error(Exception("Error on mail send"))}
                                ).bodyToFlux(Unit::class.java)

    }

}
