package com.perflog.domain.review.dto

import java.time.LocalDateTime

class ReviewDto {
    // 리뷰 생성
    data class CreateRequest(
        val memberId: Long,
        val perfumeId: Long,
        val rating: Int,
        val content: String
    )

    // 리뷰 수정
    data class UpdateRequest(
        val memberId: Long,
        val perfumeId: Long,
        val rating: Int,
        val content: String
    )

    // 응답 공통 DTO
    data class Response(
        val id: Long,
        val memberId: Long,
        val perfumeId: Long,
        val rating: Int,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )

    // 향수별 리뷰 통계
    data class Summary(
        val perfumeId: Long,
        val averageRating: Double, // 평균 평점
        val ratingDistribution: Map<Int, Int> // 평점별 개수 분포
    )
}