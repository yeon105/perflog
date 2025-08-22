package com.perflog.domain.member.repository

import com.perflog.domain.member.model.Member
import com.perflog.domain.member.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByMember(member: Member)
}