package com.perflog.config.security.jwt

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil(
    @Value("\${auth.jwt.secret.key}") secretKey: String
) {
    private val secretKey: SecretKey =
        SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")

    fun getEmail(token: String): String? {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        return claims["email"] as? String
    }

    fun getRole(token: String): String? {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        return claims["role"] as? String
    }

    fun getTokenType(token: String): String? {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        return claims["type"] as? String
    }

    fun isExpired(token: String): Boolean {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        return claims.expiration.before(Date())
    }

    // Access Token 생성 (짧은 만료시간)
    fun createAccessToken(email: String, role: String): String {
        return Jwts.builder()
            .claim("email", email)
            .claim("role", role)
            .claim("type", "access")
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 15 * 60 * 1000L)) // 15분
            .signWith(secretKey)
            .compact()
    }

    // Refresh Token 생성 (긴 만료시간)
    fun createRefreshToken(email: String): String {
        return Jwts.builder()
            .claim("email", email)
            .claim("type", "refresh")
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000L)) // 1일
            .signWith(secretKey)
            .compact()
    }
}
