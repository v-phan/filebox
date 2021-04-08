package no.system.filebox.account.adapter.out.persistence;

import no.system.filebox.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AccountRepository : JpaRepository<Account, Int> {
    @Query("Select a from Account a where a.username = :username")
    fun findByUsername(@Param("username") username: String): Account?
}
