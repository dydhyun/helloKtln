package com.example.helloKtln.blog.controller

import com.example.helloKtln.blog.dto.BlogDto
import com.example.helloKtln.blog.entity.Wordcount
import com.example.helloKtln.blog.service.BlogService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RequestMapping("/api/blog")
@RestController
class BlogController(
    val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto): String? {
        val result = blogService.searchKakao(blogDto)
        return result
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()

    //    @PostMapping("/search")
//    fun searchBlog(@RequestBody query: String): String? {
//        val blogDto = BlogDto(query, "RECENCY", 1, 1)
//        println("검색 키워드: ${blogDto.query}")
//
//        // 검색 결과 처리 (예제에서는 단순 메시지)
//        val searchResult = blogService.searchKakao(blogDto)
//        println("검색 성공: $searchResult")
//
//        return searchResult
//    }
    @PostMapping("/search")
    fun searchBlog(@RequestBody query: String): ResponseEntity<Map<String, String?>> {
        val blogDto = BlogDto(query, "RECENCY", 1, 1)
        val searchResult = blogService.searchKakao(blogDto)

        val response = mapOf("searchResults" to searchResult)
        return ResponseEntity.ok(response) // JSON 형식으로 반환
    }

}