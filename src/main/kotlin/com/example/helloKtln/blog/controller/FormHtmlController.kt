package com.example.helloKtln.blog.controller

import com.example.helloKtln.blog.dto.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class FormHtmlController {

    @RequestMapping("/form","/form/{userId}", method = [RequestMethod.GET, RequestMethod.POST])
    fun form(@PathVariable("userId")userId: Long?, user: User?, model: Model):String{

        user?.let{
            println(user)

            val initUser = User()

            model.addAttribute("user", initUser)

            return "form/signup"
        }

        val user = User(null, null, null)

        if (userId != null){
            user.name = "hello"
            user.email = "hello@kotlin.com"
            user.password = "helloPw!"
        }

        model.addAttribute("user", user)

        return "form/signup"
    }

}