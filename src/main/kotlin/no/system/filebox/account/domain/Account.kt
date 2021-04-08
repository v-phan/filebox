package no.system.filebox.account.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Account(var username: String,
              var password: String,
              @Id val userID: Int)