package no.system.filebox.account.adapter.out.persistence

import no.system.filebox.account.application.port.`in`.GenerateTokenUseCase
import no.system.filebox.account.application.port.out.CreateAccountPort
import no.system.filebox.account.application.port.out.GenerateTokenPort
import no.system.filebox.account.application.port.out.LoadAccountPort
import no.system.filebox.account.domain.Account
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class AccountPersistenceAdapter(private val accountRepository: AccountRepository) : LoadAccountPort, GenerateTokenPort,
    CreateAccountPort {
    override fun loadAccount(username: String): Account? = accountRepository.findByUsername(username)

    override fun generateToken(username: String): Account? = accountRepository.findByUsername(username)

    override fun createAccount(username: String, password: String, userID: Int): Account = accountRepository.save(Account(username, password, userID))

}