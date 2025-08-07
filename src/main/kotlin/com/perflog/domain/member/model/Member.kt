package com.perflog.domain.member.model

import com.perflog.domain.review.model.Review
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(

    @Column(name = "email", nullable = false, length = 100)
    var email: String,

    @Column(name = "password", nullable = false, length = 100)
    var password: String,

    @Column(name = "name", nullable = false, length = 10)
    var name: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: MemberRole
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToMany(mappedBy = "member")
    var reviews: MutableList<Review> = mutableListOf()
}