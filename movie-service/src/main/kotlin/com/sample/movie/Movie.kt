package com.sample.movie

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Movie(
    @Id
    var id: String? = null,
    var title: String? = null,
    val year: Int? = null,
    val rating: Double? = null,
    val genre: String? = null
)
