package com.romanidze.studeeper.modules.files.routes

import com.romanidze.studeeper.modules.files.handlers.FileInfoHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class FilesRoutes {

    @Bean
    fun filesRouter(handler: FileInfoHandler) = 
             router {

                (accept(MediaType.APPLICATION_JSON) and "/api").nest {
                    
                    "/files".nest{

                        GET("/internal", handler::getFilesInfo)
                        GET("/external", handler::listExternalFiles)

                    }

                }    

             }

}