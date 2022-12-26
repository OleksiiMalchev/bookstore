package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.ShipmentReqDTO;
import com.javacourse.bookstore.domain.dto.ShipmentRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ShipmentService {
    List<ShipmentRespDTO> getAllShipment();

    Optional<ShipmentRespDTO> getShipmentById(Long idShipment);

    Optional<ShipmentRespDTO> createShipment(ShipmentReqDTO shipmentReqDTO);

    Optional<ShipmentRespDTO> updateShipment(Long idShipment, ShipmentReqDTO shipmentReqDTO);

    Optional<ShipmentRespDTO> deleteShipment(Long idShipment);
}
