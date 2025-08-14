package com.perflog.domain.perfume.repository

import com.perflog.domain.perfume.model.entity.Perfume
import com.perflog.domain.perfume.model.entity.PerfumeTag
import org.springframework.data.jpa.repository.JpaRepository

interface PerfumeTagRepository : JpaRepository<PerfumeTag, Long> {
    fun findByPerfume(perfume: Perfume): List<PerfumeTag>
}