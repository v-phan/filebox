package no.system.filebox.account.application.port.`in`

import no.system.filebox.account.domain.Account

interface CreateAccountUseCase {

    fun createAccount(username: String, password: String, userID : Int ): Account
}