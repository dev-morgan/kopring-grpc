package com.sample.movie

import com.sample.models.MovieDto
import com.sample.models.MovieSearchRequest
import com.sample.models.MovieSearchResponse
import com.sample.models.MovieServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.transaction.annotation.Transactional

@GrpcService
class MovieService(
    val movieRepository: MovieRepository
) : MovieServiceGrpc.MovieServiceImplBase() {

    @Transactional(readOnly = true)
    override fun getMovies(request: MovieSearchRequest, responseObserver: StreamObserver<MovieSearchResponse>) {
        val movies = movieRepository.findByGenreOrderByReleaseDesc(request.genre.toString())
            .map { movie ->
                MovieDto.newBuilder().apply {
                    title = movie.title
                    release = movie.release!!
                    rating = movie.rating!!
                }.build()
            }.toList()

        val result = MovieSearchResponse.newBuilder().addAllMovie(movies).build()
        responseObserver.onNext(result)
        responseObserver.onCompleted()
    }
}