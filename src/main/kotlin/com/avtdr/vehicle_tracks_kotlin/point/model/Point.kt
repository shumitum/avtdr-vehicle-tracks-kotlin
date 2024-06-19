package com.avtdr.vehicle_tracks_kotlin.point.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.ZonedDateTime

@Entity
@Table(name = "point", schema = "public")
class Point(
    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var pointId: Long? = null,
    @NotNull
    @Column(name = "video_id")
    var videoId: Long,
    @Column(name = "location", columnDefinition = "geometry(Point,4326)")
    var location: org.locationtech.jts.geom.Point,
    @NotNull
    @Column(name = "bearing")
    @Schema(description = "Угол направления движения в градусах", example = "150.83277893066406")
    var bearing: Double,

    @NotNull
    @Column(name = "velocity")
    @Schema(description = "Скорость транспортного средства м/c", example = "8.058734893798828")
    var velocity: Double,

    @NotNull
    @Column(name = "point_datetime")
    @Schema(description = "Время проезда точки", example = "2023-06-19 06:55:20Z")
    var pointDateTime: ZonedDateTime
)