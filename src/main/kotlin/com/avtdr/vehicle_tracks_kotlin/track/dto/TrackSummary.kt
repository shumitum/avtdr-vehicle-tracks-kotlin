package com.avtdr.vehicle_tracks_kotlin.track.dto

interface TrackSummary {
    fun getTrackId(): Long?

    fun getVideoId(): Long?

    fun getDuration(): Long?

    fun getAvgVelocity(): Double?

    fun getDistance(): Float?
}