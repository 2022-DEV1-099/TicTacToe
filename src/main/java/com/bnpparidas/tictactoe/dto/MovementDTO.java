package com.bnpparidas.tictactoe.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovementDTO {

    private Character simbol;

    private PositionDTO position;

    public Character getSimbol() {
        return simbol;
    }

    public void setSimbol(Character simbol) {
        this.simbol = simbol;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }
}
