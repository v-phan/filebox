package no.system.filebox.account.application.service

import no.system.filebox.account.application.port.`in`.CreateAccountUseCase
import no.system.filebox.account.application.port.out.CreateAccountPort
import no.system.filebox.account.domain.Account
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateAccountService(private val createAccountPort: CreateAccountPort, private val passwordEncoder: BCryptPasswordEncoder) :  CreateAccountUseCase {

    override fun createAccount(username: String, password: String, userID: Int): Account = createAccountPort.createAccount(username,passwordEncoder.encode(password), userID)

}