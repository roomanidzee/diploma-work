package com.romanidze.studeeper.modules.worker.routes

import com.romanidze.studeeper.modules.worker.handlers.WorkerInfoHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 02.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class WorkerInfoRoutes{

    @Bean
    fun workerInfoRouter(handler: WorkerInfoHandler) = 
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/worker".nest {
                        GET("/{worker_id}/groupmates", handler::getWorkerGroupmates)
                    }

                }

            }

}