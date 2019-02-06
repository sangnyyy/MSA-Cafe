package me.sml.order.util;

public enum ReturnType {

    OK(0),
    FAIL(100);

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
