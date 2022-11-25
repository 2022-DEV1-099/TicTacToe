package com.bnpparidas.tictactoe.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PlayerDTO {

    private String name;
    private Character simbol;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSimbol() {
        return simbol;
    }

    public void setSimbol(Character simbol) {
        this.simbol = simbol;
    }
}
