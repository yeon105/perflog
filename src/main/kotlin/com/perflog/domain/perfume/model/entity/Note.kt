package com.perflog.domain.perfume.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "notes")
class Note(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String
)