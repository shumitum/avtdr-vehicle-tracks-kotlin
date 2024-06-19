package com.avtdr.vehicle_tracks_kotlin.geo.config

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(GeometrySerializer())
        return JsonMapper.builder()
            .addModule(simpleModule)
            .addModule(JavaTimeModule()
                .addSerializer(ZonedDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))))
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

            .build()
    }
}