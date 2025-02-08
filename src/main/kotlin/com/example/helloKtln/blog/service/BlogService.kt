package com.example.helloKtln.blog.service

import com.example.helloKtln.blog.dto.BlogDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.WebUtils

@Service
class BlogService {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey:String

    fun searchKakao(blogDto: BlogDto): String? {
//        println(blogDto.toString())
//        return "SearchKakao"
        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = webClient
            .get()
            .uri {it.path("v2/search/blog")
                .queryParam("query", blogDto.query)
                .queryParam("sort", blogDto.sort)
                .queryParam("page", blogDto.page)
                .queryParam("size", blogDto.size)
                .build()}
            .header("Authorization", "KakaoAK ${restApiKey}")// api 키 전달하는 header 부분
            .retrieve()
            .bodyToMono<String>()

        val result = response.block()

        return result
    }
}


//curl -v -G GET "https://dapi.kakao.com/v2/search/blog" \
//--data-urlencode "query=https://brunch.co.kr/@tourism 집짓기" \
//-H "Authorization: KakaoAK ${REST_API_KEY}"