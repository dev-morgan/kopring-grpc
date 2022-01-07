package com.sample.user

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    var username: String? = null,
    var nick: String? = null,
    var genre: String? = null
)