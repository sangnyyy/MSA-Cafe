package me.sml.msa.domain.model;

import lombok.Data;

@Data
public class OrderStatusResponseDTO {
    private String stat;
    private String message;
    private Object data;
    private int code;
}
