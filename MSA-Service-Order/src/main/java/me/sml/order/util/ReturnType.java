package me.sml.order.util;

public enum ReturnType {

    OK(200),
    CREATED(201),
    SERVER_ERROR(500);

    private int value;

    ReturnType(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public String getKey(){
        return name();
    }
}
