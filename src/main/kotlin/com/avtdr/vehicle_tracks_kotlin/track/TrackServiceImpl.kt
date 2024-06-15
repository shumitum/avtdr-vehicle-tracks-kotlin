package com.avtdr.vehicle_tracks_kotlin.track

import com.avtdr.vehicle_tracks_kotlin.point.PointRepository
import org.springframework.stereotype.Service

@Service
class TrackServiceImpl(private val pointRepository: PointRepository) : TrackService {

}