package com.anabada.dto.request_dto;

import com.anabada.entity.Orders;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersInsertDto {

    private Long productNo;
    private boolean take;

    public Orders getOrders() {
        Product product = Product
                .builder()
                .productNo(productNo)
                .build();
        Orders orders = Orders.builder()
                .orderTake(take)
                .product(product)
                .build();

        return orders;
    }
}
