package com.perflog.domain.member.service

import com.perflog.domain.member.dto.MemberDto
import org.springframework.security.core.Authentication

interface MemberService {

    /**
     * 새로운 회원을 등록한다.
     */
    fun createMember(request: MemberDto.CreateRequest)

    /**
     * 특정 회원의 상세 정보를 조회한다.
     *
     * @return 향수 상세 응답 DTO
     */
    fun getMember(authentication: Authentication): MemberDto.MemberResponse
}
