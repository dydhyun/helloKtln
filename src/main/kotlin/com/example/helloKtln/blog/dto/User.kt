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
    var pswd: String? = null
) : UserDetails {
    var role = ROLE_TYPE.MEMBER

    override fun getUsername(): String = name!!

    override fun getPassword(): String = pswd!!

    // 계정 만료 안됨으로 리턴
    override fun isAccountNonExpired(): Boolean = true

    // 계정 잠김 않음으로 리턴
    override fun isAccountNonLocked(): Boolean = true

    // 비밀번호 만료 안됨으로 리턴
    override fun isCredentialsNonExpired(): Boolean = true

    // 계정 활성화 됨으로 리턴
    override fun isEnabled(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()

        when (role) {
            ROLE_TYPE.ADMIN -> authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
            ROLE_TYPE.MEMBER -> authorities.add(SimpleGrantedAuthority("ROLE_MEMBER"))
        }
        return authorities
    }


}
