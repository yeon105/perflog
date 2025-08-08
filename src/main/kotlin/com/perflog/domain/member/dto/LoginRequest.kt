package com.perflog.domain.member.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class LoginRequest(
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    val email: String,
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    val password: String
)
