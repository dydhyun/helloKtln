package jpabasic.helloktl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloKtlApplication

fun main(args: Array<String>) {
    runApplication<HelloKtlApplication>(*args)

    println("hello kotlin")

}
