package com.perflog.domain.review.repository

import com.perflog.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
    fun existsByUserIdAndPerfumeId(userId: Long, perfumeId: Long): Boolean
    fun findByPerfumeId(perfumeId: Long): List<Review>
}