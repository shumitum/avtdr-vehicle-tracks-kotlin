package com.avtdr.vehicle_tracks_kotlin.geo.config

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GeometryMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(GeometrySerializer())
        return JsonMapper.builder()
            .addModule(simpleModule)
            .addModule(JavaTimeModule())
            .build()
    }
}