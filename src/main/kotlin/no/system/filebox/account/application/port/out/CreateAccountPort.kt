package no.system.filebox.account.application.port.out

import no.system.filebox.account.domain.Account
import org.springframework.stereotype.Component

@Component
interface CreateAccountPort {
    fun createAccount(username: String, password: String, userID: Int): Account
}