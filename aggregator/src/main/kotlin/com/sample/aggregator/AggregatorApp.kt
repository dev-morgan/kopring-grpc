package com.sample.aggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AggregatorApp

fun main(args: Array<String>) {
    runApplication<AggregatorApp>(*args)
}
