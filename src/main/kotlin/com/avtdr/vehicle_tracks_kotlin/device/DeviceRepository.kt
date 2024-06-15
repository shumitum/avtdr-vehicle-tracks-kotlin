package com.avtdr.vehicle_tracks_kotlin.device

import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<Device, String>
