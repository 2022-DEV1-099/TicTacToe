package com.bnpparidas.tictactoe.dto;

public class ResponseGameDTO {

    private String message;

    private Character[][] dashboard;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Character[][] getDashboard() {
        return dashboard;
    }

    public void setDashboard(Character[][] dashboard) {
        this.dashboard = dashboard;
    }
}
