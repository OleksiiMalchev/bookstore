package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.*;
import com.javacourse.bookstore.repositories.CustomerRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.repositories.WarehouseRepository;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderDetailsService;
import com.javacourse.bookstore.services.OrderService;
import com.javacourse.bookstore.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements BuyService {
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final OrderDetailsService orderDetailsService;
    private final CustomerRepository customerRepository;
    private final WarehouseRepository warehouseRepository;
    private final ShipmentService shipmentService;


    @Transactional
    @Override
    public BuyRespDTO buyBook(BuyReqDTO buyReqDTO) throws IOException {
        Long customerId = buyReqDTO.getCustomerId();
        if (customerId != null && customerRepository.existsById(customerId)) {
            Long idOrder = orderService.createOrder(buyReqDTO.getCustomerId()).get().getId();

            List<BuyReqDTO.ProductDTO> productId = buyReqDTO.getProducts();
            for (BuyReqDTO.ProductDTO l : productId) {
                if (l != null && productRepository.existsById(l.getProductId())) {
                    Warehouse warehouse = warehouseRepository.findWarehouseByProductId(l.getProductId()).get();
                    Integer stokeBookQuantity = warehouse.getBookQuantity();
                    Integer qw = l.getQuantity();
                    if (stokeBookQuantity >= qw) {
                        OrderDetails orderDetailsToSave = OrderDetails.builder()
                                .orderId(idOrder).productId(l.getProductId())
                                .quantity(qw).build();
                        orderDetailsService.createOrderDetails(orderDetailsToSave);
                        warehouse.setBookQuantity(stokeBookQuantity - qw);
                        warehouse.setReserve(qw);
                    } else {
                        throw new IOException("Not enough books in stock. In stock" + stokeBookQuantity);
                    }
                } else {
                    throw new IOException("There is no such product.");
                }
            }
            ShipmentReqDTO shipmentReqDTO = ShipmentReqDTO.builder().address(buyReqDTO.getAddress())
                    .orderId(idOrder).build();
            Optional<ShipmentRespDTO> shipment = shipmentService.createShipment(shipmentReqDTO);
            orderService.updateOrder(idOrder, OrderStatus.COMPLETED);
            return BuyRespDTO.builder()
                    .order(orderService.getOrderById(idOrder).get())
                    .shipmentAddress(shipment.get())
                    .build();
        } else {
            throw new IOException("Register to buy books.Please.");
        }
    }
}
