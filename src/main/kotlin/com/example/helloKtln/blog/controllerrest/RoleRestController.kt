package com.example.helloKtln.blog.controllerrest

import com.example.helloKtln.blog.dto.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/role")
@RestController
class RoleRestController {

    @GetMapping("/member")
    fun helloMember(@AuthenticationPrincipal user: User): ResponseEntity<Map<String, Any>> {
        val response = mutableMapOf("user" to user, "message" to "Hello Member!")
        response["message"] = "Hello, ${user.name}"

        return ResponseEntity.ok(response)
    }

    @GetMapping("/admin")
    fun helloAdmin(@AuthenticationPrincipal user: User): ResponseEntity<Map<String, Any>> {
        val response = mutableMapOf("user" to user, "message" to "Hello, ${user.name}")

        return ResponseEntity.ok(response)
    }
}
