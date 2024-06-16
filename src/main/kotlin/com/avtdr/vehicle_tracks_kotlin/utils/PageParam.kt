package com.avtdr.vehicle_tracks_kotlin.utils

import org.springframework.data.domain.PageRequest

object PageParam {
    fun of(from: Int, size: Int): PageRequest = PageRequest.of(if (from > 0) from else 0, size)
}