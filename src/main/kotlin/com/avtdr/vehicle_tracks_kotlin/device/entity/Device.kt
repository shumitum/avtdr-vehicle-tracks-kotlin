package com.avtdr.vehicle_tracks_kotlin.device.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.ZonedDateTime

@Entity
@Table(name = "device", schema = "public")
class Device(
    @Id
    @Column(name = "device_id")
    var deviceId: String? = null,

    @NotNull
    @Column(name = "import_date")
    var importDate: ZonedDateTime = ZonedDateTime.now(),

    @Column(name = "description")
    var description: String
)