package com.sample.user

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    var login: String? = null,
    var name: String? = null,
    var genre: String? = null
)