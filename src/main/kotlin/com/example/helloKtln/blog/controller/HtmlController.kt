package com.example.helloKtln.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HtmlController {

//    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    @GetMapping("/")
    fun index(): String {
        println("connect localhost 9999")
        return "index"
    }

}