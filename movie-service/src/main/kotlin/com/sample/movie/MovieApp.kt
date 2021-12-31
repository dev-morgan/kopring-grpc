package com.sample.movie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieApp

fun main(args: Array<String>) {
    runApplication<MovieApp>(*args)
}
