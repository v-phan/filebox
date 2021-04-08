package no.system.filebox.account.application.port.`in`

import org.springframework.security.core.userdetails.UserDetails

//Brukes kun internt for å generere userDetails klassen i Spring security.
interface LoadAccountUseCase {

    fun loadAccount(username: String) : UserDetails?
}