package com.sample.movie

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByGenreOrderByReleaseDesc(genre: String): List<Movie>
}
