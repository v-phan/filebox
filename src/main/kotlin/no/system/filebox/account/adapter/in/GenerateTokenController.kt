package no.system.filebox.account.adapter.`in`

import no.system.filebox.account.application.port.`in`.GenerateTokenUseCase
import no.system.filebox.account.domain.AuthenticationRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class GenerateTokenController(private val generateTokenUseCase: GenerateTokenUseCase) {

    @PostMapping("/authenticate")
    fun generateToken(@RequestBody authenticationReq : AuthenticationRequest): ResponseEntity<Any>{
        val authResponse = generateTokenUseCase.generateToken(authenticationReq)!!
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authResponse.jwt)
    }
}