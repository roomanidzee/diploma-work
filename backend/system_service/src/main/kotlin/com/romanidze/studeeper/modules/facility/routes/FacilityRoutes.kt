package com.romanidze.studeeper.modules.facility.routes

import com.romanidze.studeeper.modules.facility.handlers.FacilityHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 01.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class FacilityRoutes{

    @Bean
    fun facilityRouter(handler: FacilityHandler) = 
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/facility".nest{

                        POST("/create_facility", handler::makeFacility)
                        GET("/show_students", handler::showStudents)
                        GET("/analyse/{file_id}", handler::launchAnalysis)

                    }

                }    

            }

}