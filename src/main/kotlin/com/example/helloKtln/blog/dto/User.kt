package com.example.helloKtln.blog.dto

import com.example.helloKtln.blog.domain.ROLE_TYPE
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null
) : UserDetails{
    var role = ROLE_TYPE.MEMBER

    override fun getUsername(): String = name!!

    override fun getPassword(): String = password!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()

        when(role){
            ROLE_TYPE.ADMIN -> authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
            ROLE_TYPE.MEMBER -> authorities.add(SimpleGrantedAuthority("ROLE_MEMBER"))
        }

        return authorities
    }


}
