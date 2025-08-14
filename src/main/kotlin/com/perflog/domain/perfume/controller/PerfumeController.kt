package com.perflog.domain.perfume.controller

import com.perflog.domain.perfume.dto.PerfumeDto
import com.perflog.domain.perfume.service.PerfumeServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/perfumes")
class PerfumeController(
    private val perfumeService: PerfumeServiceImpl
) {
    @PostMapping
    fun create(@RequestBody request: PerfumeDto.CreateRequest): ResponseEntity<Void> {
        perfumeService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<PerfumeDto.Response> {
        return ResponseEntity.ok(perfumeService.get(id))
    }

    @GetMapping
    fun list(): ResponseEntity<PerfumeDto.ListResponse> {
        return ResponseEntity.ok(perfumeService.list())
    }
}