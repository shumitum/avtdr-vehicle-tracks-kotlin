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
    var trackId: Long? = null,

    @NotNull
    @Column(name = "video_id", unique = true)
    var videoId: Long,

    @NotNull
    @Column(name = "video_creation_date")
    var videoCreationDate: ZonedDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    var device: Device
)