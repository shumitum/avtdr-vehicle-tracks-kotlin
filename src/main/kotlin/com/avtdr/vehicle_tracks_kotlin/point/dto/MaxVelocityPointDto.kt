package com.avtdr.vehicle_tracks_kotlin.point.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.locationtech.jts.geom.Point
import java.time.ZonedDateTime

class MaxVelocityPointDto(
    @Schema(description = "ID точки", example = "1")
    var pointId: Long,

    @Schema(description = "Координаты точки lon, lat", example = "49.2257039, 55.8704392")
    var location: Point,

    @Schema(description = "Скорость транспортного средства м/c", example = "8.058734893798828")
    var velocity: Double,

    @Schema(description = "Время проезда точки", example = "2023-06-19 06:55:20Z")
    var pointDateTime: ZonedDateTime
)