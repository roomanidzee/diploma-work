package com.romanidze.studeeper.modules.user.routes

import com.romanidze.studeeper.modules.user.handlers.ProfileHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 *
 * Routes for profile module
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Component
class ProfileRoutes {

    @Bean
    fun profileRouter(profileHandler: ProfileHandler) =
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/admin/profiles".nest{

                        GET("/", profileHandler::all)
                        POST("/new", profileHandler::create)

                    }

                    "/info/profile".nest {
                        GET("/", profileHandler::getProfileInfo)
                    }

                }

            }

}