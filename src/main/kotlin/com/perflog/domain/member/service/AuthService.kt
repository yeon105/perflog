package com.perflog.domain.member.service

import com.perflog.config.security.jwt.JwtUtil
import com.perflog.domain.member.dto.TokenResponse
import com.perflog.domain.member.repository.MemberRepository
import com.perflog.domain.member.repository.RefreshTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class AuthService(
    private val jwtUtil: JwtUtil,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val memberRepository: MemberRepository
) {

    fun refreshAccessToken(refreshToken: String): TokenResponse? {
        return try {
            // Refresh Token 검증
            if (jwtUtil.isExpired(refreshToken)) {
                return null
            }

            val tokenType = jwtUtil.getTokenType(refreshToken)
            if (tokenType != "refresh") {
                return null
            }

            val email = jwtUtil.getEmail(refreshToken) ?: return null

            // DB에서 Refresh Token 확인
            val storedToken = refreshTokenRepository.findByToken(refreshToken) ?: return null
            if (storedToken.member.email != email || storedToken.expiresAt.isBefore(LocalDateTime.now())) {
                refreshTokenRepository.delete(storedToken)
                return null
            }

            // 사용자 정보 조회
            val member = memberRepository.findByEmail(email) ?: return null

            // 새로운 Access Token 생성
            val newAccessToken = jwtUtil.createAccessToken(email, member.role.toString())

            TokenResponse(
                accessToken = newAccessToken,
                refreshToken = refreshToken // 기존 Refresh Token 재사용
            )
        } catch (e: Exception) {
            null
        }
    }

    fun revokeRefreshToken(refreshToken: String) {
        refreshTokenRepository.findByToken(refreshToken)?.let { token ->
            refreshTokenRepository.delete(token)
        }
    }
}