package com.example.helloKtln.blog.service

import com.example.helloKtln.blog.dto.BlogDto
import com.example.helloKtln.blog.entity.Wordcount
import com.example.helloKtln.blog.repository.WordRepository
import com.example.helloKtln.core.exception.InvalidInputException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.WebUtils

@Service
class BlogService (
    val wordRepository: WordRepository
){
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey:String

    fun searchKakao(blogDto: BlogDto): String? {
//        println(blogDto.toString())
//        return "SearchKakao"
//        val msgList = mutableListOf<ExceptionMsg>()
//
//        if (blogDto.query.trim().isEmpty()){
//            msgList.add(ExceptionMsg.EMPTY_QUERY)
//        }
//
//        if (blogDto.sort.trim() !in arrayOf("ACCURACY","RECENCY")){
//            msgList.add(ExceptionMsg.NOT_IN_SORT)
//        }
//
//        when {
//            blogDto.page < 1 -> msgList.add(ExceptionMsg.LESS_THEN_MIN)
//            blogDto.page > 50 -> msgList.add(ExceptionMsg.MORE_THEN_MAX)
//        }
//
//        if (msgList.isNotEmpty()){
//            val message = msgList.joinToString { it.msg }
//            throw InvalidInputException(message)
//        }

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

        // searchKeyword to lowercase and count
        val lowQuery: String = blogDto.query!!.lowercase()

        val word: Wordcount = wordRepository.findById(lowQuery).orElse(Wordcount(lowQuery))

        word.cnt++

        wordRepository.save(word)

        return result
    }

    fun searchWordRank(): List<Wordcount> = wordRepository.findTop10ByOrderByCntDesc()
}

//curl -v -G GET "https://dapi.kakao.com/v2/search/blog" \
//--data-urlencode "query=https://brunch.co.kr/@tourism 집짓기" \
//-H "Authorization: KakaoAK ${REST_API_KEY}"

//private enum class ExceptionMsg(val msg:String){
//    EMPTY_QUERY("query parameter required"),
//    NOT_IN_SORT("sort parameter one of accuracy and recency"),
//    LESS_THEN_MIN("page is less than min"),
//    MORE_THEN_MAX("page is more than max")
//}