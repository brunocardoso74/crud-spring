package com.bruno.crud_spring.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // toString() method
    @Override
    public String toString() {
        return this.value;
    }
}
