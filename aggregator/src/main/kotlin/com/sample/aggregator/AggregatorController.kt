package com.sample.aggregator

import org.springframework.web.bind.annotation.*

@RestController
class AggregatorController(
    val userMovieService: UserMovieService
) {

    @GetMapping("/users/{loginId}")
    fun getMovies(@PathVariable loginId: String): List<RecommendMovie> {
        return userMovieService.getUserMovieSuggestions(loginId)
    }

    @PutMapping("/users")
    fun changeUserGenre(@RequestBody userGenre: UserGenre) {
        userMovieService.changeUserGenre(userGenre)
    }
}