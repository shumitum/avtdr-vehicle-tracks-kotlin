package com.avtdr.vehicle_tracks_kotlin.track

import com.avtdr.vehicle_tracks_kotlin.device.DeviceService
import com.avtdr.vehicle_tracks_kotlin.point.PointRepository
import com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto
import com.avtdr.vehicle_tracks_kotlin.point.model.Point
import com.avtdr.vehicle_tracks_kotlin.track.dto.TrackSummary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class TrackServiceImpl(
    val pointRepository: PointRepository,
    val deviceService: DeviceService
) : TrackService {

    @Transactional(readOnly = true)
    override fun getTrackPoints(
        deviceId: String?,
        rangeStart: ZonedDateTime?,
        rangeEnd: ZonedDateTime?,
        from: Int,
        size: Int
    ): List<Point?> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getMaxVelocityPoint(deviceId: String?): MaxVelocityPointDto {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getAllTracks(): List<TrackSummary> {
        return pointRepository.findAllTrackSummary()
    }

    @Transactional(readOnly = true)
    override fun getPointsWithinRadius(lon: Double?, lat: Double?, radius: Double?): List<Point> {
        TODO("Not yet implemented")
    }

}