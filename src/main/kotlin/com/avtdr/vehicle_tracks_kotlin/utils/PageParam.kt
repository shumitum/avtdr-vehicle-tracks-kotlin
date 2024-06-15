package com.avtdr.vehicle_tracks_kotlin.utils

import org.springframework.data.domain.PageRequest


object PageParam {

    fun of(from: Int, size: Int): PageRequest {
        if (from > 0) {
            return PageRequest.of(from, size)
        }
        return PageRequest.of(0, size)
    }
}