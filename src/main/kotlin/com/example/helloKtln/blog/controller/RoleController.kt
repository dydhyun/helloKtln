package com.example.helloKtln.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RoleController {

    @GetMapping("/member")
    fun helloMember():String{
        return "role/member"
    }

    @GetMapping("/admin")
    fun helloAdmin():String{
        return "role/admin"
    }
}