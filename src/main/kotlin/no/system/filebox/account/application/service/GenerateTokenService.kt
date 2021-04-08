package no.system.filebox.account.application.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import no.system.filebox.account.application.port.`in`.GenerateTokenUseCase
import no.system.filebox.account.application.port.out.GenerateTokenPort
import no.system.filebox.account.domain.AuthenticationRequest
import no.system.filebox.account.domain.AuthenticationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class GenerateTokenService(@Value("\${jwt.secret.key}") private val SECRET_KEY: String, private val generateTokenPort: GenerateTokenPort, private val authenticationManager: AuthenticationManager): GenerateTokenUseCase {

    override fun generateToken(req: AuthenticationRequest): AuthenticationResponse? {
        println("Ja1")
        try{
            println("Ja2")
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(req.username, req.password))
        }catch (e: BadCredentialsException){
            println("Incorrect username or password")
            throw Exception("Incorrect username or password", e)
        }catch (e: Exception){
            println("Something else is wrong")
            throw Exception("Something else is wrong", e)
        }
        var account = generateTokenPort.generateToken(req.username) ?: return null
        var jwt = createToken(HashMap(), User(account.username,account.password, mutableListOf()).username)
        return AuthenticationResponse(jwt)
    }


    internal fun createToken(claims: MutableMap<String, Any>, subject : String): String =
        Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 *60 * 10))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact()
}