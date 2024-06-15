package com.avtdr.vehicle_tracks_kotlin.track.model

import com.avtdr.vehicle_tracks_kotlin.device.Device
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.ZonedDateTime

@Entity
@Table(name = "track", schema = "public")
class Track(
    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var trackId: Long,

    @NotNull
    @Column(name = "video_id", unique = true)
    private var videoId: Long,

    @NotNull
    @Column(name = "video_creation_date")
    private var videoCreationDate: ZonedDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    private var device: Device
)