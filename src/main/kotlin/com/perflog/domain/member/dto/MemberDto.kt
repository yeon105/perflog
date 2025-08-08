package com.perflog.domain.member.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class MemberDto(
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    var email: String,
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    var password: String,
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    var name: String,
)
