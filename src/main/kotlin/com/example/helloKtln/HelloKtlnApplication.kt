package com.example.helloKtln

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloKtlnApplication

fun main(args: Array<String>) {
	runApplication<HelloKtlnApplication>(*args)
	println("hello kotlin")
}
