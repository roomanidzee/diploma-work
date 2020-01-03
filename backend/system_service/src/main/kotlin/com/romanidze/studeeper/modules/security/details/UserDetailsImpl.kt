package com.romanidze.studeeper.modules.security.details

import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.enums.State
import com.romanidze.studeeper.modules.user.domain.User

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 17.11.2019
 *
 * Details of user in system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 */
class UserDetailsImpl(private var user: User?): UserDetails {

    constructor(id: String, roles: List<String>, state: String, username: String) : this(null) {

        val existedRoles = enumValues<Role>().map { it.name }
        val roleCondition: Boolean = existedRoles.any { item -> roles.contains(item) }

        val existedStates = enumValues<State>().map { it.name }
        val stateCondition: Boolean = existedStates.any {item -> item == state}

        require(roleCondition) { "No roles like $roles in system" }

        require(stateCondition) { "No state $state in system" }

        this.user = User(
                id = id,
                username = username,
                roles = roles,
                state = state,
                password = ""
        )

    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

        return this.user!!.roles!!.map {
            SimpleGrantedAuthority(it)
        }.toMutableList()

    }

    override fun isEnabled(): Boolean {
        return State.valueOf(this.user!!.state!!) == State.CONFIRMED
    }

    override fun getUsername(): String {
        return this.user!!.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return State.valueOf(this.user!!.state!!) != State.NOT_CONFIRMED
    }

    override fun getPassword(): String {
        return this.user!!.password!!
    }

    override fun isAccountNonExpired(): Boolean {
        return State.valueOf(this.user!!.state!!) != State.DELETED
    }

    override fun isAccountNonLocked(): Boolean {
        return State.valueOf(this.user!!.state!!) != State.BANNED
    }

    fun getUser(): User = this.user!!
}