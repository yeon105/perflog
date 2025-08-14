package com.perflog.domain.perfume.repository

import com.perflog.domain.perfume.model.entity.Perfume
import com.perflog.domain.perfume.model.entity.PerfumeNote
import org.springframework.data.jpa.repository.JpaRepository

interface PerfumeNoteRepository : JpaRepository<PerfumeNote, Long> {
    fun findByPerfume(perfume: Perfume): List<PerfumeNote>
}