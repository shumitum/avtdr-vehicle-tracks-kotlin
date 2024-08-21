package com.avtdr.vehicle_tracks_kotlin

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    fun postGISContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer(
            DockerImageName.parse("postgis/postgis:15-3.4-alpine")
                .asCompatibleSubstituteFor("postgres")
        )
    }

}
