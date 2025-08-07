package com.perflog.domain.review.model

import com.perflog.common.model.BaseTimeEntity
import jakarta.persistence.*

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
    var content: String
) : BaseTimeEntity()