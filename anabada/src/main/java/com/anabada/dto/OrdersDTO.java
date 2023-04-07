package com.anabada.dto;

import com.anabada.entity.Member;
import com.anabada.entity.Orders;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDTO {

    private Member member;
    private Product product;
    private boolean take;

    public Orders getOrders() {
        Orders orders = Orders.builder()
                .orderTake(take)
                .member(member)
                .product(product)
                .build();

        return getOrders();
    }
}
