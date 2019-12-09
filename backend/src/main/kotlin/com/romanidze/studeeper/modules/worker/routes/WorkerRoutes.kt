package com.romanidze.studeeper.modules.worker.routes

import com.romanidze.studeeper.modules.worker.handlers.WorkerHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 09.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class WorkerRoutes {

    @Bean
    fun workerRouter(workerHandler: WorkerHandler) =
            router {
                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/worker/records".nest {
                        GET("/", workerHandler::getNewRecords)
                    }

                }
            }

}