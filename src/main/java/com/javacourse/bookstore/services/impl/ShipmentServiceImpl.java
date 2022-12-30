package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.dto.ShipmentReqDTO;
import com.javacourse.bookstore.domain.dto.ShipmentRespDTO;
import com.javacourse.bookstore.mappers.MapperForShipment;
import com.javacourse.bookstore.repositories.ShipmentRepository;
import com.javacourse.bookstore.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final MapperForShipment mapperForShipment;

    @Override
    public List<ShipmentRespDTO> getAllShipment() {
        return shipmentRepository.findAll()
                .stream()
                .map(mapperForShipment::shipmentToShipmentRespDTO)
                .toList();
    }

    @Override
    public Optional<ShipmentRespDTO> getShipmentById(Long idShipment) {
        return shipmentRepository.findById(idShipment)
                .map(mapperForShipment::shipmentToShipmentRespDTO);
    }

    @Override
    public ShipmentRespDTO createShipment(ShipmentReqDTO shipmentReqDTO) {
        return mapperForShipment.shipmentReqDtoToShipment(shipmentReqDTO)
                .map(shipmentRepository::save)
                .map(mapperForShipment::shipmentToShipmentRespDTO).get();
    }
    @Transactional
    @Override
    public Optional<ShipmentRespDTO> updateShipment(Long idShipment, ShipmentReqDTO shipmentReqDTO) {
        return shipmentRepository.findById(idShipment)
                .map(s -> {
                    s.setAddress(shipmentReqDTO.getAddress());
                    return s;
                })
                .map(mapperForShipment::shipmentToShipmentRespDTO);
    }

    @Override
    public Optional<ShipmentRespDTO> deleteShipment(Long idShipment) {
        Optional<ShipmentRespDTO> shipmentRespDTO = shipmentRepository.findById(idShipment)
                .map(mapperForShipment::shipmentToShipmentRespDTO);
        if (shipmentRespDTO.isPresent()) {
            shipmentRepository.deleteById(idShipment);
        }
        return shipmentRespDTO;
    }
}
