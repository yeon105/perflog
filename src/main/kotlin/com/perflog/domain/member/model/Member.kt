package com.perflog.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: MemberRole
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}