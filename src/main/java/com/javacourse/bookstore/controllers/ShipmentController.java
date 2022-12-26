package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.ShipmentReqDTO;
import com.javacourse.bookstore.domain.dto.ShipmentRespDTO;
import com.javacourse.bookstore.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping("/shipments")
    public ResponseEntity<? super List<ShipmentRespDTO>> getAllShipment() {
        List<ShipmentRespDTO> shipmentRespDTO = shipmentService.getAllShipment();
        if (shipmentRespDTO.isEmpty()) {
            return new ResponseEntity<>("Shipment not found!", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(shipmentRespDTO);
        }
    }

    @GetMapping("/shipments/{id}")
    public ResponseEntity<? super ShipmentRespDTO> getShipmentById(@PathVariable("id") Long idShipment) {
        Optional<ShipmentRespDTO> shipmentRespDTO = shipmentService.getShipmentById(idShipment);
        if (shipmentRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(shipmentRespDTO);
        } else {
            return new ResponseEntity<>("Shipment not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/shipments")
    public ResponseEntity<? super ShipmentRespDTO> createShipment(@RequestBody(required = false)
                                                                  ShipmentReqDTO shipmentReqDTO) {
        return checkingCreate(shipmentService.createShipment(shipmentReqDTO));
    }

    @PutMapping("/shipments/{id}")
    public ResponseEntity<? super ShipmentRespDTO> updateShipment(@PathVariable("id") Long idShipment,
                                                                  @RequestBody(required = false) ShipmentReqDTO shipmentReqDTO) {
        return checking(shipmentService.updateShipment(idShipment, shipmentReqDTO));
    }

    @DeleteMapping("/shipments/{id}")
    public ResponseEntity<? super ShipmentRespDTO> deleteShipments(@PathVariable("id") Long idShipment) {
        return checking(shipmentService.deleteShipment(idShipment));
    }

    private static ResponseEntity<? super ShipmentRespDTO> checkingCreate(Optional<ShipmentRespDTO> shipmentRespDTO) {
        if (shipmentRespDTO.isPresent()) {
            return ResponseEntity.status(201).body(shipmentRespDTO);
        } else {
            return new ResponseEntity<>("Shipment can not be creating", HttpStatus.NOT_FOUND);
        }
    }

    private static ResponseEntity<? super ShipmentRespDTO> checking(Optional<ShipmentRespDTO> shipmentRespDTO) {
        if (shipmentRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(shipmentRespDTO);
        } else {
            return new ResponseEntity<>("Shipment not found!", HttpStatus.NOT_FOUND);
        }
    }

}
