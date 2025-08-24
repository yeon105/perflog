package com.perflog.domain.member.service

import com.perflog.common.error.CustomException
import com.perflog.common.error.ErrorCode
import com.perflog.domain.member.dto.MemberDto
import com.perflog.domain.member.model.Member
import com.perflog.domain.member.model.MemberRole
import com.perflog.domain.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) : MemberService {

    override fun createMember(request: MemberDto.CreateRequest) {
        if (memberRepository.existsByEmail(request.email)) {
            throw CustomException(ErrorCode.DUPLICATE_EMAIL)
        }
        val encodedPassword = passwordEncoder.encode(request.password)
        val member = Member(request.email, encodedPassword, request.name, MemberRole.ROLE_USER)
        memberRepository.save(member)
    }

    override fun getMember(authentication: Authentication): MemberDto.MemberResponse {
        val email = authentication.name
        val member = memberRepository.findByEmail(email)
            ?: throw CustomException(ErrorCode.MEMBER_NOT_FOUND)
        
        return MemberDto.MemberResponse(
            member.email,
            member.name,
            member.createdAt,
            member.updatedAt
        )
    }
}
