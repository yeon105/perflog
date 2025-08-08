package com.perflog.domain.review.controller

import com.perflog.domain.review.dto.ReviewDto
import com.perflog.domain.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reviews")
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping
    fun createReview(@RequestBody request: ReviewDto.CreateRequest): ResponseEntity<Void> {
        reviewService.createReview(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody request: ReviewDto.UpdateRequest
    ): ResponseEntity<ReviewDto.Response> {
        val response = reviewService.updateReview(reviewId, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable reviewId: Long): ResponseEntity<Void> {
        reviewService.deleteReview(reviewId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/perfume/{perfumeId}")
    fun getReviewsByPerfume(@PathVariable perfumeId: Long): ResponseEntity<List<ReviewDto.Response>> {
        val reviews = reviewService.getReviewsByPerfume(perfumeId)
        return ResponseEntity.ok(reviews)
    }

    @GetMapping("/perfume/{perfumeId}/summary")
    fun getReviewSummary(@PathVariable perfumeId: Long): ResponseEntity<ReviewDto.Summary> {
        val summary = reviewService.getReviewSummary(perfumeId)
        return ResponseEntity.ok(summary)
    }
}