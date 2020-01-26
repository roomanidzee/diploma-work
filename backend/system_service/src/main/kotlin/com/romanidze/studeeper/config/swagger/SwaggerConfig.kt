package com.romanidze.studeeper.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

/**
 * 26.01.2020
 *
 * Swagger configuration for system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableSwagger2WebFlux
class SwaggerConfig {

    @Bean
    fun apiConfig(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build() 

}
