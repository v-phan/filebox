package no.system.filebox.account.adapter.`in`

import no.system.filebox.account.application.port.`in`.CreateAccountUseCase
import no.system.filebox.account.domain.Account
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class CreateAccountController(private val createAccountUseCase: CreateAccountUseCase) {

    @PostMapping("/signup")
    fun signup(@RequestBody user: Account): ResponseEntity<Any>{
        val account = createAccountUseCase.createAccount(user.username, user.password, user.userID)
        return ResponseEntity.ok("OK, account created")
    }
}