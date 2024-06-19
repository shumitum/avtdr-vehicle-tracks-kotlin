package com.avtdr.vehicle_tracks_kotlin.geo

import com.avtdr.vehicle_tracks_kotlin.point.PointRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GeoServiceImpl(private val pointRepository: PointRepository) : GeoService {

    @Transactional(readOnly = true)
    override fun getAllTracksGeoJson(): String {
        return pointRepository.findTracksJsonRepresentation() ?: throw NoSuchElementException("В БД нет треков")
    }
}