package com.avtdr.vehicle_tracks_kotlin.track

import com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto
import com.avtdr.vehicle_tracks_kotlin.point.model.Point
import com.avtdr.vehicle_tracks_kotlin.track.dto.TrackSummary
import com.avtdr.vehicle_tracks_kotlin.track.validation.TimeValidationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@Validated
@RestController
@RequestMapping("/tracks")
@Tag(name = "ТРЕКИ ТРАНСПОРТНЫХ СРЕДСТВ", description = "API для работы с треками движения транспортных средств")
class TrackController(
    val trackService: TrackService,
    val timeValidationService: TimeValidationService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/points")
    @ResponseStatus(HttpStatus.OK)
    fun getTrackPoints(
        @Parameter(description = "ID устройства", example = "32e59c906a958812")
        @RequestParam(name = "deviceId", required = false) deviceId: String?,
        @Parameter(description = "Дата и время начала выборки (ZonedDateTime)", example = "2023-06-19T06:01:00Z")
        @RequestParam(name = "rangeStart", required = false) rangeStart: ZonedDateTime?,
        @Parameter(description = "Дату и время конца выборки (ZonedDateTime)", example = "2023-06-19T06:02:00Z")
        @RequestParam(name = "rangeEnd", required = false) rangeEnd: ZonedDateTime?,
        @Parameter(description = "Номер страницы")
        @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero from: Int,
        @Parameter(description = "Количество элементов на странице")
        @RequestParam(name = "size", defaultValue = "10") @Positive size: Int
    ): List<Point> {
        log.info(
            "Запрос на получение списка точек с параметрами deviceId=$deviceId, rangeStart=$rangeStart, " +
                    "rangeEnd=$rangeEnd, from=$from, size=$size"
        )
        timeValidationService.checkStartTimeIsBeforeEndTime(rangeStart, rangeEnd)
        return trackService.getTrackPoints(deviceId, rangeStart, rangeEnd, from, size)
    }

    @GetMapping("/device/{deviceId}/max-velocity-point")
    @ResponseStatus(HttpStatus.OK)
    fun getMaxVelocityPoint(
        @Parameter(description = "ID устройства", example = "32e59c906a958812")
        @PathVariable("deviceId") deviceId: String
    ): MaxVelocityPointDto {
        log.info("Запрос на получение точки с максимальной скоростью устройства с ID=$deviceId")
        return trackService.getMaxVelocityPoint(deviceId)
    }

    @GetMapping("/all")
    @Operation(
        summary = "Запрос данных по каждому треку",
        description = "Данный эндпоинт возвращает список треков, где для каждого трека будет указана длительность" +
                " трека, средняя скорость и пройденное расстояние"
    )
    fun getAllTracks(): List<TrackSummary> {
        log.info("Запрос на получение списка треков")
        return trackService.getAllTracks()
    }

    @GetMapping("/points-within-radius")
    @ResponseStatus(HttpStatus.OK)
    fun getPointsWithinRadius(
        @Parameter(description = "Долгота точки в градусах", example = "49.1025455")
        @RequestParam(name = "lon") @Min(-180) @Max(+180) lon: Double,
        @Parameter(description = "Широта точки в градусах", example = "55.7964352")
        @RequestParam(name = "lat") @Min(-90) @Max(+90) lat: Double,
        @Parameter(description = "Радиус поиска в метрах", example = "20")
        @RequestParam(name = "radius") @Positive radius: Double
    ): List<Point> {
        return trackService.getPointsWithinRadius(lon, lat, radius)
    }

}