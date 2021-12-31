package com.sample.user

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id
    var login: String? = null,
    var name: String? = null,
    var genre: String? = null
)