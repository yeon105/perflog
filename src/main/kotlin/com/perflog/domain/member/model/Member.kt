package com.perflog.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "member")
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
    @Column(name = "id", nullable = false)
    var id: Long? = null
}