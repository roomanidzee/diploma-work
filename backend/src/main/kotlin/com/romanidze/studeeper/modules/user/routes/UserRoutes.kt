package com.romanidze.studeeper.modules.user.routes

import com.romanidze.studeeper.modules.user.handlers.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class UserRoutes {

    @Bean
    fun userRouter(userHandler: UserHandler) =
            router {
                (accept(MediaType.APPLICATION_JSON) and "/api").nest {
                    "/users".nest {
                        GET("/", userHandler::all)
                        POST("/new", userHandler::create)
                    }
                }
            }

}