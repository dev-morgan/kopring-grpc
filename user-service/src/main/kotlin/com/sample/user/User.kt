package com.sample.user

import com.sample.models.common.Genre
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
) {
    fun getGenre(): Genre {
        return when (this.genre) {
            null -> Genre.UNKNOWN
            else -> Genre.valueOf(this.genre!!.uppercase())
        }
    }
}