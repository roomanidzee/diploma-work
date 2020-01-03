package com.romanidze.studeeper.modules.employer.routes

import com.romanidze.studeeper.modules.employer.handlers.EmployerRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class RequestsRoutes {

    @Bean
    fun requestsRouter(handler: EmployerRequestHandler) =
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/worker".nest {
                        GET("/{worker_id}/requests", handler::getByWorker)

                        "/requests".nest {
                            GET("/{id}", handler::getByID)
                            GET("/filter", handler::getByStatus)
                            POST("/create", handler::createRequest)
                            PUT("/update", handler::updateRequest)
                        }

                    }

                    "/employer".nest {
                        GET("/{employer_id}/requests", handler::getByEmployer)

                        "/requests".nest {
                            GET("/{id}", handler::getByID)
                            GET("/filter", handler::getByStatus)
                            POST("/create", handler::createRequest)
                            PUT("/update", handler::updateRequest)
                        }

                    }

                }

            }

}