package com.perflog.domain.review.model

import com.perflog.common.model.BaseTimeEntity
import com.perflog.domain.member.model.Member
import jakarta.persistence.*

@Entity
@Table(name = "reviews")
class Review(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @Column(name = "perfume_id", nullable = false)
    val perfumeId: Long = 0,

    @Column(nullable = false)
    var rating: Int,

    @Column(columnDefinition = "TEXT")
    var content: String
) : BaseTimeEntity()