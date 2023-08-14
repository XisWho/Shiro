package com.xw.constant;

public enum TokenType {

    NORMAL("NORMAL"), ADMIN("ADMIN");

    private String type;

    TokenType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
