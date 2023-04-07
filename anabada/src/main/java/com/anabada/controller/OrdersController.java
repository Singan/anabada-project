package com.anabada.controller;

import com.anabada.dto.OrdersDTO;
import com.anabada.entity.Orders;
import com.anabada.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersRepository ordersRepository;

    @PostMapping("/orders")
    public Orders orderInsert(@RequestBody OrdersDTO ordersDTO) {
        Orders orders = ordersDTO.getOrders();
        return ordersRepository.save(orders);
    }
}
