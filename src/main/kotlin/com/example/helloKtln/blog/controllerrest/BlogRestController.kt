package com.example.helloKtln.blog.controllerrest

import com.example.helloKtln.blog.dto.BlogDto
import com.example.helloKtln.blog.entity.Wordcount
import com.example.helloKtln.blog.service.BlogService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/blog")
@RestController
class BlogRestController(
    val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto): String? {
        val result = blogService.searchKakao(blogDto)
        return result
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()

    @PostMapping("/search")
    fun searchBlog(@RequestBody query: String): ResponseEntity<Map<String, Any?>> {
        val blogDto = BlogDto(query, "RECENCY", 1, 6)
        val searchResult = blogService.searchKakao(blogDto)

        val objectMapper = jacksonObjectMapper()
        val jsonResult = objectMapper.convertValue(searchResult, Any::class.java)

        val response = mapOf("searchResults" to jsonResult)
        println(response)

        return ResponseEntity.ok(response) // JSON 형식으로 반환
    }


}