package com.perflog.domain.review

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reviews")
class Review(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long = 0,

    @Column(name = "perfume_id", nullable = false)
    val perfumeId: Long = 0,

    @Column(nullable = false)
    var rating: Int,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
