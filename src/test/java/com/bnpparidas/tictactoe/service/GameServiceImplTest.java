package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class GameServiceImplTest {

    private GameService gameService;

    private DashboardServiceImpl dashboardService;

    @BeforeEach
    void init() {
        dashboardService = new DashboardServiceImpl();
        gameService = new GameServiceImpl(dashboardService);
    }

    @Test
    public void startGame_successTest(){

        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");
        player1.setSimbol('X');

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);

        Assertions.assertTrue(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player1NullTest(){

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(null,player2);

        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player2NullTest(){

        PlayerDTO player1= new PlayerDTO();
        player1.setName("natalia");
        player1.setSimbol('O');

        gameService.startGame(player1,null);

        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player1NotSymbolTest(){

        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);

        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void makeMovement_validMovementTest() throws TicTacException {

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(position);



        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);

        boolean result = gameService.makeMovement(movementDTO);
        Assertions.assertTrue(result);
    }

    @Test
    public void makeMovement_invalidMovementGameDidntStartTest() throws TicTacException {

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(position);

        boolean result = gameService.makeMovement(movementDTO);
        Assertions.assertTrue(result);
    }

    @Test
    public void makeMovement_invalidMovementIncorrectSymbolTest() throws TicTacException {

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('T');
        movementDTO.setPosition(position);


        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }


    @Test
    public void makeMovement_invalidPositionMovementTest() throws TicTacException {

        PositionDTO position = new PositionDTO();
        position.setRowPosition(10);
        position.setColPosition(10);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('T');
        movementDTO.setPosition(position);


        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }
}
