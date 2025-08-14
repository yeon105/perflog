package com.perflog.domain.perfume.service

import com.perflog.domain.perfume.dto.PerfumeDto

interface PerfumeService {

    /**
     * 새로운 향수를 등록한다.
     */
    fun create(request: PerfumeDto.CreateRequest)

    /**
     * 특정 향수의 상세 정보를 조회한다.
     *
     * @param id 향수 ID
     * @return 향수 상세 응답 DTO
     */
    fun get(id: Long): PerfumeDto.Response

    /**
     * 모든 향수의 간단한 정보를 조회한다.
     *
     * @return 향수 목록 응답 DTO
     */
    fun list(): PerfumeDto.ListResponse
}
