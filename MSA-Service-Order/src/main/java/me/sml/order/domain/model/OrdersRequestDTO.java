package me.sml.order.domain.model;

import lombok.Data;

@Data
public class OrdersRequestDTO {

    private String coffeeName;
    private int coffeeCount;
    private String customerName;

    public Orders toEntity() {
        return Orders.builder()
                .coffeeCount(coffeeCount)
                .coffeeName(coffeeName)
                .customerName(customerName)
                .build();
    }
}
