package com.sample.movie

import com.sample.models.MovieDto
import com.sample.models.MovieSearchRequest
import com.sample.models.MovieSearchResponse
import com.sample.models.MovieServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class MovieService(
    val movieRepository: MovieRepository
) : MovieServiceGrpc.MovieServiceImplBase() {
    override fun getMovies(request: MovieSearchRequest, responseObserver: StreamObserver<MovieSearchResponse>) {
        val movies = movieRepository.findByGenreOrderByYearDesc(request.genre.toString())
            .map { movie ->
                MovieDto.newBuilder().apply {
                    title = movie.title
                    year = movie.year!!.toInt()
                    rating = movie.rating!!
                }.build()
            }.toList()

        val result = MovieSearchResponse.newBuilder().addAllMovie(movies).build()
        responseObserver.onNext(result)
        responseObserver.onCompleted()
    }
}