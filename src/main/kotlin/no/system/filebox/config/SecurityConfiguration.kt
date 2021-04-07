package no.system.filebox.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.stereotype.Component

@Component
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf()
//            .disable()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //uncomment for enable supply av csrf token
//        http
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .anonymous()
        http.cors()
    }
}