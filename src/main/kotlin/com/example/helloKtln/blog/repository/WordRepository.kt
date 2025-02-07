package com.example.helloKtln.blog.repository

import com.example.helloKtln.blog.entity.Wordcount
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<Wordcount, String> {
}