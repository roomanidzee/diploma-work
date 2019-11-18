package com.romanidze.studeeper.modules.security.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 18.11.2019
 *
 * Class for work with jwt authentication instance
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
class JWTAuthentication(private val token: String?) : Authentication {

    private var isAuthenticated: Boolean = false
    private var userDetails: UserDetails? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.userDetails!!.authorities
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    override fun getName(): String {
        return this.token!!
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return null
    }

    override fun isAuthenticated(): Boolean {
        return this.isAuthenticated
    }

    override fun getDetails(): UserDetails? {
        return this.userDetails
    }

    fun setUserDetails(userDetails: UserDetails){
        this.userDetails = userDetails
    }


}