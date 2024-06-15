package com.avtdr.vehicle_tracks_kotlin.device


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceServiceImpl(val deviceRepository: DeviceRepository) : DeviceService {

    @Transactional(readOnly = true)
    override fun checkDeviceExistence(deviceID: String?) {
        if (deviceID != null && !deviceRepository.existsById(deviceID)) {
            throw NoSuchElementException("Устройства с deviceID=$deviceID не существует")
        }
    }
}