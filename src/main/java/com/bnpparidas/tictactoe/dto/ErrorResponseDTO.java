package com.bnpparidas.tictactoe.dto;

public class ErrorResponseDTO {

    private String errorResponse;

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
