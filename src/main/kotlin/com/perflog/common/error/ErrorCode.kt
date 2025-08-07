package com.perflog.common.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // 공통
    ENTITY_NOT_FOUND(404, "요청한 엔티티를 찾을 수 없습니다."),
    INVALID_ARGUMENT(400, "요청 파라미터가 유효하지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다."),

    // 인증/인가 (Security, JWT)
    EMAIL_NOT_FOUND(404, "존재하지 않는 이메일입니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(401, "인증이 필요합니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(401, "지원하지 않는 토큰 형식입니다."),
    TOKEN_MISSING(401, "토큰이 존재하지 않습니다."),

    // 회원
    DUPLICATE_EMAIL(409, "이미 가입된 이메일입니다."),
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),

    // 리뷰
    REVIEW_ALREADY_EXISTS(409, "이미 해당 향수에 대한 리뷰가 존재합니다."),
    INVALID_RATING(400, "평점은 1~5 사이여야 합니다."),
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다.")
}