package com.romanidze.studeeper.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

/**
 * 19.11.2019
 *
 * Web configuration for all parts of system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableWebFlux
class WebConfig: WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("Content-Language", "EXPIRES")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("http://0.0.0.0")
    }

}