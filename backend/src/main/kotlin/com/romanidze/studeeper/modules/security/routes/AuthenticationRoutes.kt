package com.romanidze.studeeper.modules.security.routes

import com.romanidze.studeeper.modules.security.handlers.AuthenticationHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 19.11.2019
 *
 * Routes for authentication requests
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class AuthenticationRoutes {

    @Bean
    fun authenticationRouter(handler: AuthenticationHandler) =
            router {
                (accept(MediaType.APPLICATION_JSON) and "/api").nest {
                    POST("/security/login", handler::authenticate)
                    GET("/security/check_token", handler::checkToken)
                }
            }

}