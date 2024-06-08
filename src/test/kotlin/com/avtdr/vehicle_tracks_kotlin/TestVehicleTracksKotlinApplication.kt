package com.avtdr.vehicle_tracks_kotlin

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<VehicleTracksKotlinApplication>().with(TestcontainersConfiguration::class).run(*args)
}
