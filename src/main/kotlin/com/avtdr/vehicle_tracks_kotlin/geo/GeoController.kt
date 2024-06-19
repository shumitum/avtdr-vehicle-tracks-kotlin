package com.avtdr.vehicle_tracks_kotlin.geo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/geo")
@ResponseStatus(HttpStatus.OK)
class GeoController(private val geoService: GeoService) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/tracks/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllTracksGeoJson(): String {
        log.info("Запрос всех треков в формате GeoJson")
        return geoService.getAllTracksGeoJson()
    }
}