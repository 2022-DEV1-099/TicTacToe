package com.bnpparidas.tictactoe.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameDTO {

    private PlayerDTO player1DTO;

    private PlayerDTO player2DTO;

    public PlayerDTO getPlayer2DTO() {
        return player2DTO;
    }

    public void setPlayer2DTO(PlayerDTO player2DTO) {
        this.player2DTO = player2DTO;
    }

    public PlayerDTO getPlayer1DTO() {
        return player1DTO;
    }

    public void setPlayer1DTO(PlayerDTO player1DTO) {
        this.player1DTO = player1DTO;
    }
}
