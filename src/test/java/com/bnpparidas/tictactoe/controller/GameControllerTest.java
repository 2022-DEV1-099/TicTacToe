package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.dto.GameDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Test
    public void startGameSuccessfully(){

        PlayerDTO player1 = new PlayerDTO();
        player1.setName("javier");
        player1.setSimbol('X');
        PlayerDTO player2 = new PlayerDTO();
        player2.setName("maria");
        player2.setSimbol('O');


        GameDTO gameDTO = new GameDTO();
        gameDTO.setPlayer1DTO(player1);
        gameDTO.setPlayer2DTO(player2);

        ResponseEntity responseEntity = gameController.startGame(gameDTO);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }


    @Test
    public void startGameUnSuccessfully_sameSimbol(){

        PlayerDTO player1 = new PlayerDTO();
        player1.setName("javier");
        player1.setSimbol('O');
        PlayerDTO player2 = new PlayerDTO();
        player2.setName("maria");
        player2.setSimbol('O');


        GameDTO gameDTO = new GameDTO();
        gameDTO.setPlayer1DTO(player1);
        gameDTO.setPlayer2DTO(player2);

        ResponseEntity responseEntity = gameController.startGame(gameDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }


    @Test
    public void startGameUnSuccessfully_emptySimbol(){

        PlayerDTO player1 = new PlayerDTO();
        player1.setName("javier");
        PlayerDTO player2 = new PlayerDTO();
        player2.setName("maria");
        player2.setSimbol('O');


        GameDTO gameDTO = new GameDTO();
        gameDTO.setPlayer1DTO(player1);
        gameDTO.setPlayer2DTO(player2);

        ResponseEntity responseEntity = gameController.startGame(gameDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test
    public void startGameUnSuccessfully_emptyplayer(){

        PlayerDTO player1 = new PlayerDTO();
        player1.setName("javier");
        PlayerDTO player2 = new PlayerDTO();
        player2.setName("maria");
        player2.setSimbol('O');


        GameDTO gameDTO = new GameDTO();
        gameDTO.setPlayer2DTO(player2);

        ResponseEntity responseEntity = gameController.startGame(gameDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }
}
