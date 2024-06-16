package com.avtdr.vehicle_tracks_kotlin.track.validation

import com.avtdr.vehicle_tracks_kotlin.exception.TimeValidationException
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class TimeValidationServiceImpl : TimeValidationService {
    override fun checkStartTimeIsBeforeEndTime(rangeStart: ZonedDateTime?, rangeEnd: ZonedDateTime?) {
        if (rangeStart != null && rangeEnd != null && (rangeEnd.isBefore(rangeStart))) {
            throw TimeValidationException("Параметр дата и время начала выборки должен быть раньше даты и времени конца выборки")
        }
    }
}