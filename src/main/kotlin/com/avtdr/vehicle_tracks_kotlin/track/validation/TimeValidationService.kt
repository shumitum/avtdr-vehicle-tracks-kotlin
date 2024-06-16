package com.avtdr.vehicle_tracks_kotlin.track.validation

import java.time.ZonedDateTime

interface TimeValidationService {
    fun checkStartTimeIsBeforeEndTime(rangeStart: ZonedDateTime?, rangeEnd: ZonedDateTime?)
}