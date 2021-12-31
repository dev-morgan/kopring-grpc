package com.sample.aggregator

import com.sample.models.*
import com.sample.models.common.Genre
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class UserMovieService {
    @GrpcClient("user-service")
    lateinit var userStub: UserServiceGrpc.UserServiceBlockingStub

    @GrpcClient("movie-service")
    lateinit var movieStub: MovieServiceGrpc.MovieServiceBlockingStub

    fun getUserMovieSuggestions(loginId: String): List<RecommendMovie> {
        val userSearchRequest = UserSearchRequest.newBuilder().apply {
                this.loginId = loginId
            }.build()
        val userResponse = userStub.getUserGenre(userSearchRequest)
        val movieSearchRequest = MovieSearchRequest.newBuilder().apply {
                this.genre = userResponse.genre
            }.build()
        val movieSearchResponse = movieStub.getMovies(movieSearchRequest)

        return movieSearchResponse.movieList.map { movieDto ->
                RecommendMovie(movieDto.title, movieDto.year, movieDto.rating)
            }.toList()
    }

    fun changeUserGenre(userGenre: UserGenre): UserResponse {
        val userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder().apply {
            loginId = userGenre.loginId
            genre = Genre.valueOf(userGenre.genre.uppercase())
        }.build()

        return userStub.updateUserGenre(userGenreUpdateRequest)
    }
}