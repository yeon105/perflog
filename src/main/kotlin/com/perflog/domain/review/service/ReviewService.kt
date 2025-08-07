package com.perflog.domain.review.service

import com.perflog.common.error.CustomException
import com.perflog.common.error.ErrorCode
import com.perflog.domain.review.dto.ReviewDto
import com.perflog.domain.review.model.Review
import com.perflog.domain.review.repository.ReviewRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository
) {

    fun createReview(request: ReviewDto.CreateRequest): Unit {
        if (reviewRepository.existsByUserIdAndPerfumeId(request.userId, request.perfumeId)) {
            throw CustomException(ErrorCode.REVIEW_ALREADY_EXISTS)
        }

        if (request.rating !in 1..5) {
            throw CustomException(ErrorCode.INVALID_RATING)
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
            .orElseThrow { CustomException(ErrorCode.REVIEW_NOT_FOUND) }

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
        val reviews = reviewRepository.findByPerfumeId(perfumeId)
        if (reviews.isEmpty()) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        return reviews.map { review ->
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
        if (reviews.isEmpty()) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        val avg = reviews.map { it.rating }.average()
        val distribution = reviews.groupingBy { it.rating }.eachCount()

        return ReviewDto.Summary(
            perfumeId = perfumeId,
            averageRating = avg,
            ratingDistribution = distribution
        )
    }
}