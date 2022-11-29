package com.bnpparidas.tictactoe.dto;

public class MovementResponseDTO {

    private Boolean isWinner;
    private String message;
    private Character[][] dashboard;

    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(Boolean winner) {
        isWinner = winner;
    }

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
