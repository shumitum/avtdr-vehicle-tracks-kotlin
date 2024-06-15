package com.avtdr.vehicle_tracks_kotlin.track

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sound.midi.Track


@Validated
@RestController
@RequestMapping("/tracks")
@Tag(name = "ТРЕКИ ТРАНСПОРТНЫХ СРЕДСТВ", description = "API для работы с треками движения транспортных средств")
class TrackController(private val trackService: TrackService) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/all")
    @Operation(
        summary = "Запрос данных по каждому треку",
        description = "Данный эндпоинт возвращает список треков, где для каждого трека будет указана длительность" +
                " трека, средняя скорость и пройденное расстояние"
    )
    fun getAllTracks(): List<Track> {
        log.info("Запрос на получение списка треков")
        return emptyList();
    }
}