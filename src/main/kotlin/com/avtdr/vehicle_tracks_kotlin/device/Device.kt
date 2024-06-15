package com.avtdr.vehicle_tracks_kotlin.device

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
    private var deviceId: String,

    @NotNull
    @Column(name = "import_date")
    private var importDate: ZonedDateTime = ZonedDateTime.now(),

    @Column(name = "description")
    private var description: String
)