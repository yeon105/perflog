package com.perflog.domain.member.service

import com.perflog.domain.member.dto.MemberDto

interface MemberService {

    /**
     * 새로운 회원을 등록한다.
     */
    fun createMember(memberDTO: MemberDto)
}
