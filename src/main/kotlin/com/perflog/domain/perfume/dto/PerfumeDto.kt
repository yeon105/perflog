package com.perflog.domain.perfume.dto

import com.perflog.domain.perfume.model.enum.Gender
import com.perflog.domain.perfume.model.enum.Longevity
import com.perflog.domain.perfume.model.enum.NoteLayer
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
        val notes: List<NoteItem> = emptyList(),
        val tagIds: List<Long> = emptyList()
    ) {
        data class NoteItem(
            val noteId: Long,
            val type: NoteLayer
        )
    }

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
        val notes: List<NoteView>,
        val tags: List<String>
    ) {
        data class NoteView(val name: String, val type: NoteLayer)
    }

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