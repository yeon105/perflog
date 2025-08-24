package com.perflog.domain.member.service

import com.perflog.common.error.CustomException
import com.perflog.common.error.ErrorCode
import com.perflog.domain.member.dto.MemberDto
import com.perflog.domain.member.model.Member
import com.perflog.domain.member.model.MemberRole
import com.perflog.domain.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) : MemberService {
    
    override fun createMember(memberDTO: MemberDto) {
        if (memberRepository.existsByEmail(memberDTO.email)) {
            throw CustomException(ErrorCode.DUPLICATE_EMAIL)
        }
        val encodedPassword = passwordEncoder.encode(memberDTO.password)
        val member = Member(memberDTO.email, encodedPassword, memberDTO.name, MemberRole.ROLE_USER)
        memberRepository.save(member)
    }
}
