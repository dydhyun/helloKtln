package com.example.helloKtln.blog.repository

import com.example.helloKtln.blog.dto.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {


}