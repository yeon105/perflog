package com.perflog.domain.review

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
    fun existsByUserIdAndPerfumeId(userId: Long, perfumeId: Long): Boolean
    fun findByPerfumeId(perfumeId: Long): List<Review>
}