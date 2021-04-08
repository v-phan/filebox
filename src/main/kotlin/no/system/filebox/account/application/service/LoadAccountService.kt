package no.system.filebox.account.application.service

import no.system.filebox.account.application.port.`in`.LoadAccountUseCase
import no.system.filebox.account.application.port.out.LoadAccountPort
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LoadAccountService(private val loadAccountPort: LoadAccountPort) : LoadAccountUseCase, UserDetailsService {

    override fun loadAccount(username: String): UserDetails? {
        var account = loadAccountPort.loadAccount(username) ?: return null
        return User(account.username,account.password, mutableListOf())
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails =
        loadAccount(username) ?: throw UsernameNotFoundException("Unable to find user with given username")

}