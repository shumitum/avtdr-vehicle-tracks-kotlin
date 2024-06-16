package com.avtdr.vehicle_tracks_kotlin.track

import com.avtdr.vehicle_tracks_kotlin.device.DeviceService
import com.avtdr.vehicle_tracks_kotlin.point.PointRepository
import com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto
import com.avtdr.vehicle_tracks_kotlin.point.model.Point
import com.avtdr.vehicle_tracks_kotlin.track.dto.TrackSummary
import com.avtdr.vehicle_tracks_kotlin.utils.PageParam
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel
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
    ): List<Point> {
        deviceService.checkDeviceExistence(deviceId)
        return pointRepository.findTrackPoints(deviceId, rangeStart, rangeEnd, PageParam.of(from, size))
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
    override fun getPointsWithinRadius(lon: Double, lat: Double, radius: Double): List<Point> {
        val geoFactory = GeometryFactory(PrecisionModel(), 4326)
        val point = geoFactory.createPoint(Coordinate(lon, lat))
        return pointRepository.findPointsWithinRadius(point, radius)
    }
}