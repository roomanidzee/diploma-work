package com.romanidze.studeeper.modules.security.routes

import com.romanidze.studeeper.modules.security.handlers.RegistrationHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 19.11.2019
 *
 * Routes for registration requests
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class RegistrationRoutes {

    @Bean
    fun registrationRouter(handler: RegistrationHandler) =
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {
                    POST("/security/register", handler::register)
                }

            }

}