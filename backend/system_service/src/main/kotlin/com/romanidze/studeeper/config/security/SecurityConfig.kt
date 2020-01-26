package com.romanidze.studeeper.config.security

import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.filters.JWTFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository

/**
 * 19.11.2019
 *
 * Security configuration for all system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableWebFluxSecurity
class SecurityConfig(private val filter: JWTFilter) {

    private val allRoles = Role.values().map { it.toString() }

    private val swaggerURLs = arrayOf<String>(
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/security",
        "/configuration/**",
        "/swagger-ui.html",
        "/webjars/**"
    )

    @Bean
    fun securityChain(http: ServerHttpSecurity): SecurityWebFilterChain{

        http
                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                .pathMatchers(*swaggerURLs).permitAll()
                .pathMatchers("/api/security/**").permitAll()
                .pathMatchers("/api/public/**").hasAnyAuthority(*allRoles.toTypedArray())
                .pathMatchers("/api/files/**").hasAnyAuthority(*allRoles.toTypedArray())
                .pathMatchers("/api/admin/**").hasAuthority(Role.ADMIN.toString())
                .pathMatchers("/api/info/**").hasAnyAuthority(*allRoles.toTypedArray())
                .pathMatchers("/api/worker/**").hasAuthority(Role.WORKER.toString())
                .pathMatchers("/api/employer/**").hasAuthority(Role.EMPLOYER.toString())
                .pathMatchers("/api/facility/**").hasAuthority(Role.FACILITY.toString())
                .anyExchange().permitAll()
            .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()

        return http.build()

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun contextRepository(): WebSessionServerSecurityContextRepository {
        return WebSessionServerSecurityContextRepository()
    }

}