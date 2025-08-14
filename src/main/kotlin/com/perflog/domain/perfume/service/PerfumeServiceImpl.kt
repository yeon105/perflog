package com.perflog.domain.perfume.service

import com.perflog.common.error.CustomException
import com.perflog.common.error.ErrorCode
import com.perflog.domain.perfume.dto.PerfumeDto
import com.perflog.domain.perfume.model.entity.Perfume
import com.perflog.domain.perfume.model.entity.PerfumeNote
import com.perflog.domain.perfume.model.entity.PerfumeTag
import com.perflog.domain.perfume.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PerfumeServiceImpl(
    private val perfumeRepository: PerfumeRepository,
    private val noteRepository: NoteRepository,
    private val perfumeNoteRepository: PerfumeNoteRepository,
    private val tagRepository: TagRepository,
    private val perfumeTagRepository: PerfumeTagRepository
) {

    @Transactional
    fun create(request: PerfumeDto.CreateRequest) {
        if (perfumeRepository.existsByNameAndBrand(request.name, request.brand)) {
            throw CustomException(ErrorCode.DUPLICATE_PERFUME)
        }

        val noteIds = request.notes.map { it.noteId }.toSet()
        if (noteIds.isNotEmpty()) {
            val existing = noteRepository.findAllById(noteIds).map { it.id }.toSet()
            if ((noteIds - existing).isNotEmpty()) throw CustomException(ErrorCode.NOTE_NOT_FOUND)
        }

        val tagIds = request.tagIds.toSet()
        if (tagIds.isNotEmpty()) {
            val existing = tagRepository.findAllById(tagIds).map { it.id }.toSet()
            if ((tagIds - existing).isNotEmpty()) throw CustomException(ErrorCode.TAG_NOT_FOUND)
        }

        val perfume = perfumeRepository.save(
            Perfume(
                name = request.name,
                brand = request.brand,
                launchYear = request.launchYear,
                imageUrl = request.imageUrl,
                longevity = request.longevity,
                season = request.season,
                gender = request.gender
            )
        )

        val noteLinks = request.notes.map { n ->
            PerfumeNote(
                perfume = perfume,
                note = noteRepository.getReferenceById(n.noteId),
                type = n.type
            )
        }
        perfumeNoteRepository.saveAll(noteLinks)

        val tagLinks = tagIds.map { tagId ->
            PerfumeTag(
                perfume = perfume,
                tag = tagRepository.getReferenceById(tagId)
            )
        }
        perfumeTagRepository.saveAll(tagLinks)
    }

    @Transactional(readOnly = true)
    fun get(id: Long): PerfumeDto.Response {
        val perfume = perfumeRepository.findById(id)
            .orElseThrow { CustomException(ErrorCode.PERFUME_NOT_FOUND) }

        val noteLinks = perfumeNoteRepository.findByPerfume(perfume)
        val tagLinks = perfumeTagRepository.findByPerfume(perfume)

        return PerfumeDto.Response(
            id = perfume.id,
            name = perfume.name,
            brand = perfume.brand,
            launchYear = perfume.launchYear,
            imageUrl = perfume.imageUrl,
            longevity = perfume.longevity.description,
            season = perfume.season.description,
            gender = perfume.gender.description,
            notes = noteLinks.map { link ->
                PerfumeDto.Response.NoteView(
                    name = link.note.name,
                    type = link.type
                )
            },
            tags = tagLinks.map { it.tag.name }
        )
    }

    fun list(): PerfumeDto.ListResponse {
        val perfumes = perfumeRepository.findAll()

        val items = perfumes.map {
            PerfumeDto.ListResponse.PerfumeSimple(
                id = it.id,
                name = it.name,
                brand = it.brand,
                season = it.season.description,
                gender = it.gender.description
            )
        }

        return PerfumeDto.ListResponse(items = items)
    }

}