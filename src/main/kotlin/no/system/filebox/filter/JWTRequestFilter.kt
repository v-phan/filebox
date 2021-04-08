package no.system.filebox.filter

import no.system.filebox.account.application.service.LoadAccountService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JWTRequestFilter(private val jwtUtil: JWTUtil, private val loadAccountService: LoadAccountService) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class, UsernameNotFoundException::class)
    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        val authorizationHeader = httpServletRequest.getHeader("Authorization")
        if(authorizationHeader != null && authorizationHeader.startsWith("JWT ")){
            var jwt = authorizationHeader.substring(4)
            var username = jwtUtil.extractUsername(jwt)
            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails = loadAccountService.loadAccount(username) ?: throw UsernameNotFoundException("Username not found")
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }
}