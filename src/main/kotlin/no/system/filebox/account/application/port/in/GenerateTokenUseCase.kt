package no.system.filebox.account.application.port.`in`

import no.system.filebox.account.domain.AuthenticationRequest
import no.system.filebox.account.domain.AuthenticationResponse

interface GenerateTokenUseCase {

    fun generateToken(req: AuthenticationRequest): AuthenticationResponse?
}