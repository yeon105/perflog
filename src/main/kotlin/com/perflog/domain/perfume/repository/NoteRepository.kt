package com.perflog.domain.perfume.repository

import com.perflog.domain.perfume.model.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long>