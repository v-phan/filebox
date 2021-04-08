package no.system.filebox.filter

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class JWTUtil(@Value("\${jwt.secret.key}") private val SECRET_KEY: String){

    fun extractUsername(token: String): String = extractClaim(token, Function { obj: Claims -> obj.subject })

    fun extractExpiration(token: String): Date = extractClaim(token, Function { obj: Claims -> obj.expiration })

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T = claimsResolver.apply(extractAllClaims(token))

    fun extractAllClaims(token : String) : Claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body

    fun isTokenExpired(token : String) : Boolean = extractExpiration(token).before(Date())

    fun validateToken(token: String, userDetails: UserDetails) : Boolean = extractUsername(token) == userDetails.username && !isTokenExpired(token)
}
