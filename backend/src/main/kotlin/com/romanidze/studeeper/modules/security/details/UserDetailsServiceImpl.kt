package com.romanidze.studeeper.modules.security.details

import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * 17.11.2019
 *
 * Retrieve user for security needs
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository): ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails> {

        val fallback = Mono.error<User>(
                IllegalArgumentException("No user with username $username")
        )

        val user = this.userRepository.findByUsername(username!!)
                                      .switchIfEmpty(fallback)

        return user.map {
            UserDetailsImpl(it)
        }

    }
}