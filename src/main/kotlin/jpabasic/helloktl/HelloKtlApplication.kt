package jpabasic.helloktl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloKtlApplication

fun main(args: Array<String>) {
    runApplication<HelloKtlApplication>(*args)
    println("Kotlin codeStyle setting")

    println("Setting -> Editor -> CodeStyle -> Kotlin -> SetForm -> kotlinStyleGuide")
    println("Setting -> Editor -> Inspections -> General -> Incorrect formating")
}
