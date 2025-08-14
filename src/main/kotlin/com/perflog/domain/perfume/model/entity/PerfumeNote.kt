package com.perflog.domain.perfume.model.entity

import com.perflog.domain.perfume.model.enum.NoteLayer
import jakarta.persistence.*

@Entity
@Table(name = "perfume_notes")
class PerfumeNote(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id", nullable = false)
    var perfume: Perfume,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = false)
    var note: Note,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    var type: NoteLayer
)