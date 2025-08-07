package com.perflog.domain.review.repository

import com.perflog.domain.member.model.Member
import com.perflog.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
    fun existsByMemberAndPerfumeId(member: Member, perfumeId: Long): Boolean
    fun findByPerfumeId(perfumeId: Long): List<Review>
}