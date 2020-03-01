package com.romanidze.studeeper.modules.employer.routes

import com.romanidze.studeeper.modules.employer.handlers.EmployerInfoHandler
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
class InfoRoutes {

    @Bean
    fun employerInfoRouter(handler: EmployerInfoHandler) = 
        router {

            (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                "/public".nest {

                    GET("/facilities", handler::getAllFacilities)
                    GET("/facilities?facility_title={facility_title}", handler::getFacilityByTitle)
                    GET("/facilities?facility_spec={facility_spec}", handler::getFacilityBySpec)
                    GET(
                        "/facilities?facility_spec={facility_spec}&facility_grad={facility_grad}",
                        handler::getFacilityBySpecAndGrad
                    )
                    GET("/graphs", handler::getAllGraphs)

                }

            }

        }

}