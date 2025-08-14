package com.perflog.domain.perfume.repository

import com.perflog.domain.perfume.model.entity.Perfume
import org.springframework.data.jpa.repository.JpaRepository

interface PerfumeRepository : JpaRepository<Perfume, Long> {
    fun existsByNameAndBrand(name: String, brand: String): Boolean
}