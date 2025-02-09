package com.example.helloKtln.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class FormHtmlController {

    @GetMapping("/form","/form/{userId}")
    fun index(@PathVariable("userId")userId: Long?, model: Model):String{
        val user = com.example.helloKtln.blog.dto.User(null, null, null)

        if (userId != null){
            user.name = "hello"
            user.email = "hello@kotlin.com"
            user.password = "helloPw!"
        }

        model.addAttribute("user", user)
        return "form/signup"
    }

}