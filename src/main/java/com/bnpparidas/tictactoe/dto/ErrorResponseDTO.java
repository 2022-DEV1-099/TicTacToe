package com.bnpparidas.tictactoe.dto;

public class ErrorResponseDTO {

    private String errorResponse;
    private Character[][] dashboard;

    public Character[][] getDashboard() {
        return dashboard;
    }

    public void setDashboard(Character[][] dashboard) {
        this.dashboard = dashboard;
    }

    public ErrorResponseDTO(String message) {
        this.setErrorResponse(message);
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
}
