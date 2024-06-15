package com.avtdr.vehicle_tracks_kotlin.track

import com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto
import com.avtdr.vehicle_tracks_kotlin.point.model.Point
import com.avtdr.vehicle_tracks_kotlin.track.dto.TrackSummary
import java.time.ZonedDateTime

interface TrackService {
    fun getTrackPoints(
        deviceId: String?,
        rangeStart: ZonedDateTime?,
        rangeEnd: ZonedDateTime?,
        from: Int,
        size: Int
    ): List<Point?>

    fun getMaxVelocityPoint(deviceId: String?): MaxVelocityPointDto

    fun getAllTracks(): List<TrackSummary>

    fun getPointsWithinRadius(lon: Double?, lat: Double?, radius: Double?): List<Point>
}