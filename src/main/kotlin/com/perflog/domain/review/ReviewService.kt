package com.perflog.domain.review

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository
) {

    fun createReview(request: ReviewDto.CreateRequest): Unit {
        if (reviewRepository.existsByUserIdAndPerfumeId(request.userId, request.perfumeId)) {
            throw IllegalStateException("이미 해당 향수에 대한 리뷰가 존재합니다.")
        }

        if (request.rating !in 1..5) {
            throw IllegalArgumentException("평점은 1-5 사이여야 합니다.")
        }

        val review = Review(
            userId = request.userId,
            perfumeId = request.perfumeId,
            rating = request.rating,
            content = request.content
        )

        reviewRepository.save(review)
    }

    fun updateReview(id: Long, request: ReviewDto.UpdateRequest): ReviewDto.Response {
        val review = reviewRepository.findById(id)
            .orElseThrow { IllegalArgumentException("리뷰를 찾을 수 없습니다.") }

        review.rating = request.rating
        review.content = request.content
        review.updatedAt = LocalDateTime.now()

        val savedReview = reviewRepository.save(review)

        return ReviewDto.Response(
            id = savedReview.id,
            userId = savedReview.userId,
            perfumeId = savedReview.perfumeId,
            rating = savedReview.rating,
            content = savedReview.content,
            createdAt = savedReview.createdAt,
            updatedAt = savedReview.updatedAt
        )
    }

    fun deleteReview(id: Long) {
        reviewRepository.deleteById(id)
    }

    fun getReviewsByPerfume(perfumeId: Long): List<ReviewDto.Response> {
        return reviewRepository.findByPerfumeId(perfumeId).map { review ->
            ReviewDto.Response(
                id = review.id,
                userId = review.userId,
                perfumeId = review.perfumeId,
                rating = review.rating,
                content = review.content,
                createdAt = review.createdAt,
                updatedAt = review.updatedAt
            )
        }
    }

    fun getReviewSummary(perfumeId: Long): ReviewDto.Summary {
        val reviews = reviewRepository.findByPerfumeId(perfumeId)
        val total = reviews.size
        val avg = if (total > 0) reviews.map { it.rating }.average() else 0.0
        val distribution = reviews.groupingBy { it.rating }.eachCount()

        return ReviewDto.Summary(
            perfumeId = perfumeId,
            averageRating = avg,
            ratingDistribution = distribution
        )
    }
}
