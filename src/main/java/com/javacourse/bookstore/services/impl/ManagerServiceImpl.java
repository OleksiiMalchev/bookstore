package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import com.javacourse.bookstore.domain.dto.ShipmentReqDTO;
import com.javacourse.bookstore.domain.dto.ShipmentRespDTO;
import com.javacourse.bookstore.repositories.*;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderService;
import com.javacourse.bookstore.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements BuyService {
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final WarehouseRepository warehouseRepository;
    private final ShipmentService shipmentService;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;


    @Transactional
    @Override
    public BuyRespDTO buyBook(BuyReqDTO buyReqDTO) throws IOException {
        Long customerId = buyReqDTO.getCustomerId();
        if (customerId != null && customerRepository.existsById(customerId)) {
            Long idOrder = orderService.createOrder(buyReqDTO.getCustomerId()).getId();
            Order orderFromBase = orderRepository.findById(idOrder).get();
            List<BuyReqDTO.ProductDTO> productId = buyReqDTO.getProducts();
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            for (BuyReqDTO.ProductDTO l : productId) {
                if (l != null && productRepository.existsById(l.getProductId())) {
                    Warehouse warehouse = warehouseRepository.findWarehouseByProductId(l.getProductId()).get();
                    Integer stokeBookQuantity = warehouse.getBookQuantity();
                    Integer qw = l.getQuantity();
                    if (stokeBookQuantity >= qw) {
                        OrderDetails orderDetailsToSave = OrderDetails.builder()
                                .orderId(idOrder).productId(l.getProductId()).product(productRepository.findById(l.getProductId()).get())
                                .quantity(qw).build();
                        OrderDetails save = orderDetailsRepository.save(orderDetailsToSave);
                        warehouse.setBookQuantity(stokeBookQuantity - qw);
                        warehouse.setSale(warehouse.getSale() + qw);
                        orderDetailsList.add(save);
                    } else {
                        throw new IOException("Not enough books in stock. In stock " + stokeBookQuantity + " book/s");
                    }
                } else {
                    throw new IOException("There is no such product.");
                }
            }
            if (buyReqDTO.getAddress() != null) {
                ShipmentReqDTO shipmentReqDTO = ShipmentReqDTO.builder().address(buyReqDTO.getAddress())
                        .orderId(idOrder).build();
                ShipmentRespDTO shipment = shipmentService.createShipment(shipmentReqDTO);
                orderService.updateOrder(orderFromBase, orderDetailsList, OrderStatus.COMPLETED);
                return BuyRespDTO.builder()
                        .order(orderService.getOrderById(idOrder).get())
                        .shipmentAddress(shipment)
                        .build();
            } else {
                throw new IOException("Your order was canceled due to lack of delivery address.");
            }
        } else {
            throw new IOException("Register to buy books.Please.");
        }
    }
}
