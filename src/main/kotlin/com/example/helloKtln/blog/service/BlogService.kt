package com.example.helloKtln.blog.service

import com.example.helloKtln.blog.dto.BlogDto
import org.springframework.stereotype.Service

@Service
class BlogService {
    fun searchKakao(blogDto: BlogDto) : String?{
        println(blogDto.toString())
        return "SearchKakao"
    }
}