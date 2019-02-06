package me.sml.order.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class OrdersResponseDTO {

    @Getter @Setter
    private String stat;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private int code;

    @Getter
    private Object data;

    public OrdersResponseDTO(Object data) {
        this.data = data;
    }
}
