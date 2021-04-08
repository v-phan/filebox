package no.system.filebox.account.application.port.out

import no.system.filebox.account.domain.Account
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
interface LoadAccountPort {
    fun loadAccount(username: String) : Account?
}