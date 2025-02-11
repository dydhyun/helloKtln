package com.example.helloKtln.blog.controller

import com.example.helloKtln.blog.dto.User
import com.example.helloKtln.blog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FormHtmlController {

    @Autowired
    lateinit var userRepository: UserRepository

    @RequestMapping("/form", "/form/{userId}", method = [RequestMethod.GET])
    fun form(@PathVariable("userId") userId: Long?, user: User?, model: Model): String {

        val user = if (userId == null) {
            User(null, null, null, null)
        } else{
            userRepository.findByIdOrNull(userId)
        }

        model.addAttribute("user", user)

        return "form/signup"
    }

    @PostMapping("/form")
    fun postForm(user: User?, model: Model): String {

        user?.let {
            userRepository.save(it)
//            userRepository.save(user)
        }
        val initUser = User()

        model.addAttribute("user", initUser)

        return "form/signup"
    }

}