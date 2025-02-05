package jpabasic.helloktl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloKtlApplication

fun main(args: Array<String>) {
    runApplication<HelloKtlApplication>(*args)
    println("Kotlin codeStyle setting")

    println("Setting -> Editor -> CodeStyle -> Kotlin -> SetForm -> kotlinStyleGuide")
    // 코틀린 스타일 가이드를 따르는지 확인하는 설정하기
    println("Setting -> Editor -> Inspections -> General -> Incorrect formating")

}
