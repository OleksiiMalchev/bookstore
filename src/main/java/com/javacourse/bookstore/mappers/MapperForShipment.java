package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Shipment;
import com.javacourse.bookstore.domain.dto.ShipmentReqDTO;
import com.javacourse.bookstore.domain.dto.ShipmentRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForShipment {
    public Optional<Shipment> shipmentReqDtoToShipment(ShipmentReqDTO shipmentReqDTO) {
        return Optional.ofNullable(shipmentReqDTO)
                .map(s -> Shipment.builder()
                        .address(shipmentReqDTO.getAddress())
                        .orderId(shipmentReqDTO.getOrderId())
                        .build());
    }

    public ShipmentRespDTO shipmentToShipmentRespDTO(Shipment shipment) {
        return Optional.ofNullable(shipment)
                .map(s -> ShipmentRespDTO.builder()
                        .id(shipment.getId())
                        .address(shipment.getAddress())
                        .orderId(shipment.getOrderId())
                        .build())
                .orElse(null);
    }
}
