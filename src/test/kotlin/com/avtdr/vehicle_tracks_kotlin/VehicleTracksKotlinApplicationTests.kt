package com.avtdr.vehicle_tracks_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class VehicleTracksKotlinApplicationTests {

	@Test
	fun contextLoads() {
	}

}
