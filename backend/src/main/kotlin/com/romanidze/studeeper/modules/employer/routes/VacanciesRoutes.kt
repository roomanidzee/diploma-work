package com.romanidze.studeeper.modules.employer.routes

import com.romanidze.studeeper.modules.employer.handlers.EmployerVacancyHandler
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
class VacanciesRoutes {

    @Bean
    fun vacancyRouter(handler: EmployerVacancyHandler) =
            router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {

                    "/public/vacancies".nest{
                        GET("/", handler::getAllVacancies)
                        GET("/{id}", handler::getByID)
                    }

                    "/employer".nest {

                        GET("/{id}/vacancies", handler::getByEmployer)

                        "/vacancies".nest {

                            POST("/create", handler::createVacancy)

                        }

                    }

                }

            }

}