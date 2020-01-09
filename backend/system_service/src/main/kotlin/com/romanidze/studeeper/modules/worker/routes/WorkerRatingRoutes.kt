package com.romanidze.studeeper.modules.worker.routes

import com.romanidze.studeeper.modules.worker.handlers.WorkerRatingHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 09.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class WorkerRatingRoutes{

    @Bean
    fun ratingRouter(handler: WorkerRatingHandler) = 
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/public".nest{
                        GET("/ratings", handler::allRatings)
                    }

                    "/employer".nest{
                        POST("/add_rating", handler::rateWorker)
                    }

                    "/facility".nest{
                        POST("/add_rating", handler::rateWorker)
                    }

                }    

            }

} 
