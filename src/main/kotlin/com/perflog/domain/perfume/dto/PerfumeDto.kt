package com.perflog.domain.perfume.dto

import com.perflog.domain.perfume.model.enum.Gender
import com.perflog.domain.perfume.model.enum.Longevity
import com.perflog.domain.perfume.model.enum.Season

class PerfumeDto {

    // 등록 요청
    data class CreateRequest(
        val name: String,
        val brand: String,
        val launchYear: Int,
        val imageUrl: String,
        val longevity: Longevity,
        val season: Season,
        val gender: Gender,
        val topNotes: List<String> = emptyList(),
        val middleNotes: List<String> = emptyList(),
        val baseNotes: List<String> = emptyList(),
        val tagIds: List<Long> = emptyList()
    )

    // 단일 향수 응답
    data class Response(
        val id: Long,
        val name: String,
        val brand: String,
        val launchYear: Int,
        val imageUrl: String,
        val longevity: String,
        val season: String,
        val gender: String,
        val topNotes: List<String>,
        val middleNotes: List<String>,
        val baseNotes: List<String>,
        val tags: List<String>
    )

    // 목록 응답
    data class ListResponse(
        val items: List<PerfumeSimple>
    ) {
        data class PerfumeSimple(
            val id: Long,
            val name: String,
            val brand: String,
            val season: String,
            val gender: String
        )
    }
}