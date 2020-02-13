package com.romanidze.studeeper.modules.email.dto

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */

data class EmailObj(
    val email: String
)

data class EmailReceivers(
    val to: Set<EmailObj>
)

data class Content(
    val type: String,
    val value: String
)

data class SendGridMailDTO(
    val personalizations: Set<EmailReceivers>,
    val from: EmailObj,
    val subject: String,
    val content: Set<Content>
){

    constructor(mailObj: MailDTO): this(
        personalizations = setOf<EmailReceivers>(
            EmailReceivers(
                to=setOf<EmailObj>(
                    email=mailObj.to
                )
            )
        ),
        from = EmailObj(email=mailObj.from),
        subject = mailObj.subject,
        content = setOf<Content>(
            Content(
                type = "text/plain",
                value = mailObj.message
            )
        )
    )

}