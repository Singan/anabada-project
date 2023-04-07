package com.anabada.controller;

import com.anabada.dto.request_dto.OrdersInsertDto;
import com.anabada.entity.Orders;
import com.anabada.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;

    @PostMapping
    public Orders orderInsert(@RequestBody OrdersInsertDto ordersInsertDto) {
        Orders orders = ordersInsertDto.getOrders();
        return ordersRepository.save(orders);
    }

}
