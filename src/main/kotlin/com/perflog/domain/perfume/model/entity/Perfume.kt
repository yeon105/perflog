package com.perflog.domain.perfume.model.entity

import com.perflog.domain.perfume.model.enum.Gender
import com.perflog.domain.perfume.model.enum.Longevity
import com.perflog.domain.perfume.model.enum.Season
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "perfumes")
class Perfume(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var brand: String,

    @Column(name = "launch_year")
    var launchYear: Int,

    @Column(name = "image_url", columnDefinition = "TEXT")
    var imageUrl: String,

    @Enumerated(EnumType.STRING)
    var longevity: Longevity,

    @Enumerated(EnumType.STRING)
    var season: Season,

    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
)