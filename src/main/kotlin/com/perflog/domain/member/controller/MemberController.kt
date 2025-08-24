package com.perflog.domain.member.controller

import com.perflog.domain.member.dto.MemberDto
import com.perflog.domain.member.service.MemberService
import com.perflog.domain.member.service.TokenService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService,
    private val tokenService: TokenService
) {

    @PostMapping("/join")
    fun createMember(@Valid @RequestBody memberDTO: MemberDto): ResponseEntity<Void> {
        memberService.createMember(memberDTO)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<Void> {
        val refreshToken = request.cookies?.find { it.name == "refreshToken" }?.value

        if (refreshToken != null) {
            tokenService.revokeRefreshToken(refreshToken)
        }

        val accessTokenCookie = Cookie("accessToken", "").apply {
            maxAge = 0
            path = "/"
            isHttpOnly = true
        }
        val refreshTokenCookie = Cookie("refreshToken", "").apply {
            maxAge = 0
            path = "/"
            isHttpOnly = true
        }

        response.addCookie(accessTokenCookie)
        response.addCookie(refreshTokenCookie)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/refresh")
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<Void> {
        val refreshToken = request.cookies?.find { it.name == "refreshToken" }?.value
            ?: return ResponseEntity.badRequest().build()

        val tokenResponse = tokenService.refreshAccessToken(refreshToken)
            ?: return ResponseEntity.unprocessableEntity().build()

        // 새로운 Access Token을 쿠키에 설정
        val accessTokenCookie = Cookie("accessToken", tokenResponse.accessToken).apply {
            isHttpOnly = true
            secure = false
            path = "/"
            maxAge = 15 * 60 // 15분
        }

        response.addCookie(accessTokenCookie)

        return ResponseEntity.ok().build()
    }
}