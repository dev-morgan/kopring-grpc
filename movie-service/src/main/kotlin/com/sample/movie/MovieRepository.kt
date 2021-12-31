package com.sample.movie

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Int> {
    fun findByGenreOrderByYearDesc(genre: String): List<Movie>
}
