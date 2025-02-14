package com.example.helloKtln.blog.config

import com.example.helloKtln.blog.service.UserCustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {

    @Autowired
    lateinit var userCustomService: UserCustomService

    // 비밀번호 암호화 설정
    @Bean
    fun encoder(): BCryptPasswordEncoder = BCryptPasswordEncoder(10)
    // 기본 강도가 10

    // DaoAuthenticationProvider 설정
    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userCustomService) // 커스텀 유저 서비스 적용
        authProvider.setPasswordEncoder(encoder()) // 비밀번호 암호화 적용
        return authProvider
    }

    // AuthenticationManager 설정 (Spring Security 5.7 이후 필수)
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    // HTTP 보안 설정
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // CSRF 보안 해제
            .authorizeHttpRequests {
                it.requestMatchers("/form/**", "/resources/**").permitAll() // 리소스는 인증 없이 접근 가능
                it.requestMatchers("/admin").hasRole("ADMIN")
                it.requestMatchers("/member").hasAnyRole("MEMBER", "ADMIN")
                it.anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            }
            .formLogin { // 로그인 설정
                it.loginPage("/login") // 커스텀 로그인 페이지 지정
                it.defaultSuccessUrl("/home", true) // 로그인 성공 시 이동할 URL
//                it.usernameParameter("username")
//                it.passwordParameter("password") 기본필드로 다음과 같이 설정
                it.permitAll()
            }
            .logout { // 로그아웃 설정
                it.logoutUrl("/logout")
                it.logoutSuccessUrl("/login?logout") // 로그아웃 성공 후 이동할 페이지
                it.invalidateHttpSession(true) // 세션 무효화
                it.deleteCookies("JSESSIONID") // 쿠키 삭제
                it.permitAll()
            }

        return http.build()
    }
}
