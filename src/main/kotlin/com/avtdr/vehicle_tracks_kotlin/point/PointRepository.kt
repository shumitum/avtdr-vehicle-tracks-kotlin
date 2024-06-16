package com.avtdr.vehicle_tracks_kotlin.point

import com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto
import com.avtdr.vehicle_tracks_kotlin.point.model.Point
import com.avtdr.vehicle_tracks_kotlin.track.dto.TrackSummary
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.ZonedDateTime
import java.util.*

interface PointRepository : JpaRepository<Point, Long> {
    @Query(
        "select p from Point as p left join Track as t on p.videoId = t.videoId " +
                "where ((:deviceId) is NULL or t.device.deviceId = :deviceId) " +
                "and (cast(:rangeStart as java.time.ZonedDateTime) is NULL or p.pointDateTime > :rangeStart) " +
                "and (cast(:rangeEnd as java.time.ZonedDateTime) is NULL or p.pointDateTime < :rangeEnd)"
    )
    fun findTrackPoints(
        @Param("deviceId") deviceId: String?,
        @Param("rangeStart") rangeStart: ZonedDateTime?,
        @Param("rangeEnd") rangeEnd: ZonedDateTime?,
        page: PageRequest
    ): List<Point>

    @Query(
        "select new com.avtdr.vehicle_tracks_kotlin.point.dto.MaxVelocityPointDto(p.pointId, p.location, p.velocity, p.pointDateTime) " +
                "from Point as p " +
                "where (p.velocity = (select max(p.velocity) from Point as p " +
                "left join Track as t on p.videoId = t.videoId " +
                "where (t.device.deviceId = :deviceId)))"
    )
    fun findMaxVelocityPointByDeviceId(@Param("deviceId") deviceId: String): Optional<MaxVelocityPointDto>

    @Query(
        value = "select track_id as trackId, p.video_id as videoId, " +
                "EXTRACT(epoch from (MAX(point_datetime) - MIN(point_datetime))) as duration, " +
                "avg(velocity) as avgVelocity, " +
                "ST_LengthSpheroid(ST_MakeLine(p.location order by p.point_id), 'SPHEROID[\"WGS 84\",6378137,298.257223563]') as distance " +
                "from point as p inner join track as t on p.video_id = t.video_id " +
                "group by p.video_id, t.track_id " +
                "order by t.track_id",
        nativeQuery = true
    )
    fun findAllTrackSummary(): List<TrackSummary>

    @Query(
        value = "select json_build_object('type', 'FeatureCollection', 'features', json_agg(ST_AsGeoJSON(g.*)\\:\\:json)) " +
                "from (select t.track_id, p.video_id, ST_MakeLine(p.location) " +
                "from point as p inner join track as t on p.video_id = t.video_id " +
                "group by p.video_id, t.track_id " +
                "order by track_id) as g",
        nativeQuery = true
    )
    fun findTracksJsonRepresentation(): String

    @Query(
        value = "select * from point " +
                "where ST_DWithin(location\\:\\:geography, :point\\:\\:geography, :radius, true) " +
                "order by st_distance(location\\:\\:geography, :point\\:\\:geography, true)",
        nativeQuery = true
    )
    fun findPointsWithinRadius(
        @Param("point") point: org.locationtech.jts.geom.Point,
        @Param("radius") radius: Double
    ): List<Point>
}