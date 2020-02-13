package com.romanidze.studeeper.modules.email.services.interfaces

import com.romanidze.studeeper.modules.email.dto.MailDTO
import reactor.core.publisher.Flux

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface EmailSendService{
    fun sendMail(mailObj: MailDTO): Flux<Unit>
}