package com.perflog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PerflogApplication

fun main(args: Array<String>) {
    runApplication<PerflogApplication>(*args)
}
