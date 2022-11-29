package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    public void startGame_successTest() throws TicTacException {

        startGame();

        Assertions.assertTrue(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player1NullTest() {

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.startGame(null,player2);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player2NullTest() throws TicTacException {

        PlayerDTO player1= new PlayerDTO();
        player1.setName("natalia");
        player1.setSimbol('O');

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.startGame(player1,null);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player1NotSymbolTest() throws TicTacException {

        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.startGame(player1,player2);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void startGame_player2NotSymbolTest() throws TicTacException {

        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");
        player1.setSimbol('O');

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.startGame(player1,player2);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void makeMovement_validMovementTest() throws TicTacException {

        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(position);

        boolean result = gameService.makeMovement(movementDTO);
        Assertions.assertFalse(result);
    }

    @Test
    public void makeMovement_invalidMovementGameDidntStartTest() throws TicTacException {

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(position);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(gameService.hasGameStarted());
    }

    @Test
    public void makeMovement_invalidMovementIncorrectSymbolTest() throws TicTacException {

        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('T');
        movementDTO.setPosition(position);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }


    @Test
    public void makeMovement_invalidPositionTest() throws TicTacException {

        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(10);
        position.setColPosition(10);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(position);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }

    @Test
    public void makeMovement_invalidMovementFirstOTest() throws TicTacException {

        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(10);
        position.setColPosition(10);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('O');
        movementDTO.setPosition(position);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }

    @Test
    public void makeMovement_invalidMovementTwoFollowedMovementTest() throws TicTacException {

        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(1);
        position.setColPosition(1);

        MovementDTO firstMovement = new MovementDTO();
        firstMovement.setSimbol('X');
        firstMovement.setPosition(position);

        gameService.makeMovement(firstMovement);

        PositionDTO secondPosition = new PositionDTO();
        secondPosition.setRowPosition(1);
        secondPosition.setColPosition(0);

        MovementDTO secondMovement = new MovementDTO();
        secondMovement.setSimbol('X');
        secondMovement.setPosition(secondPosition);

        Exception exception = assertThrows(TicTacException.class, () -> {
            gameService.makeMovement(secondMovement);
        });
        Assertions.assertNotNull(exception);

    }

    @Test
    public void makeMovement_player1WinHizontal() throws TicTacException {
        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(0);
        position.setColPosition(0);
        MovementDTO firstMovement = new MovementDTO();
        firstMovement.setSimbol('X');
        firstMovement.setPosition(position);
        Assertions.assertFalse(gameService.makeMovement(firstMovement));

        PositionDTO secondPosition = new PositionDTO();
        secondPosition.setRowPosition(1);
        secondPosition.setColPosition(0);
        MovementDTO secondMovement = new MovementDTO();
        secondMovement.setSimbol('O');
        secondMovement.setPosition(secondPosition);
        Assertions.assertFalse(gameService.makeMovement(secondMovement));

        PositionDTO thirdPosition = new PositionDTO();
        thirdPosition.setRowPosition(0);
        thirdPosition.setColPosition(1);
        MovementDTO thirdMovement = new MovementDTO();
        thirdMovement.setSimbol('X');
        thirdMovement.setPosition(thirdPosition);
        Assertions.assertFalse(gameService.makeMovement(thirdMovement));

        PositionDTO foudPosition = new PositionDTO();
        foudPosition.setRowPosition(2);
        foudPosition.setColPosition(0);
        MovementDTO fourMovement = new MovementDTO();
        fourMovement.setSimbol('O');
        fourMovement.setPosition(foudPosition);
        Assertions.assertFalse(gameService.makeMovement(fourMovement));

        PositionDTO fivePosition = new PositionDTO();
        fivePosition.setRowPosition(0);
        fivePosition.setColPosition(2);
        MovementDTO fiveMovement = new MovementDTO();
        fiveMovement.setSimbol('X');
        fiveMovement.setPosition(fivePosition);
        Assertions.assertTrue(gameService.makeMovement(fiveMovement));
    }

    @Test
    public void makeMovement_player1WinVertical() throws TicTacException {
        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(0);
        position.setColPosition(0);
        MovementDTO firstMovement = new MovementDTO();
        firstMovement.setSimbol('X');
        firstMovement.setPosition(position);
        Assertions.assertFalse(gameService.makeMovement(firstMovement));

        PositionDTO secondPosition = new PositionDTO();
        secondPosition.setRowPosition(0);
        secondPosition.setColPosition(1);
        MovementDTO secondMovement = new MovementDTO();
        secondMovement.setSimbol('O');
        secondMovement.setPosition(secondPosition);
        Assertions.assertFalse(gameService.makeMovement(secondMovement));

        PositionDTO thirdPosition = new PositionDTO();
        thirdPosition.setRowPosition(1);
        thirdPosition.setColPosition(0);
        MovementDTO thirdMovement = new MovementDTO();
        thirdMovement.setSimbol('X');
        thirdMovement.setPosition(thirdPosition);
        Assertions.assertFalse(gameService.makeMovement(thirdMovement));

        PositionDTO foudPosition = new PositionDTO();
        foudPosition.setRowPosition(0);
        foudPosition.setColPosition(2);
        MovementDTO fourMovement = new MovementDTO();
        fourMovement.setSimbol('O');
        fourMovement.setPosition(foudPosition);
        Assertions.assertFalse(gameService.makeMovement(fourMovement));

        PositionDTO fivePosition = new PositionDTO();
        fivePosition.setRowPosition(2);
        fivePosition.setColPosition(0);
        MovementDTO fiveMovement = new MovementDTO();
        fiveMovement.setSimbol('X');
        fiveMovement.setPosition(fivePosition);
        Assertions.assertTrue(gameService.makeMovement(fiveMovement));
    }

    @Test
    public void makeMovement_player1WinDiagonal() throws TicTacException {
        startGame();

        PositionDTO position = new PositionDTO();
        position.setRowPosition(0);
        position.setColPosition(0);
        MovementDTO firstMovement = new MovementDTO();
        firstMovement.setSimbol('X');
        firstMovement.setPosition(position);
        Assertions.assertFalse(gameService.makeMovement(firstMovement));

        PositionDTO secondPosition = new PositionDTO();
        secondPosition.setRowPosition(0);
        secondPosition.setColPosition(1);
        MovementDTO secondMovement = new MovementDTO();
        secondMovement.setSimbol('O');
        secondMovement.setPosition(secondPosition);
        Assertions.assertFalse(gameService.makeMovement(secondMovement));

        PositionDTO thirdPosition = new PositionDTO();
        thirdPosition.setRowPosition(1);
        thirdPosition.setColPosition(1);
        MovementDTO thirdMovement = new MovementDTO();
        thirdMovement.setSimbol('X');
        thirdMovement.setPosition(thirdPosition);
        Assertions.assertFalse(gameService.makeMovement(thirdMovement));

        PositionDTO foudPosition = new PositionDTO();
        foudPosition.setRowPosition(0);
        foudPosition.setColPosition(2);
        MovementDTO fourMovement = new MovementDTO();
        fourMovement.setSimbol('O');
        fourMovement.setPosition(foudPosition);
        Assertions.assertFalse(gameService.makeMovement(fourMovement));

        PositionDTO fivePosition = new PositionDTO();
        fivePosition.setRowPosition(2);
        fivePosition.setColPosition(2);
        MovementDTO fiveMovement = new MovementDTO();
        fiveMovement.setSimbol('X');
        fiveMovement.setPosition(fivePosition);
        Assertions.assertTrue(gameService.makeMovement(fiveMovement));
    }

    private void startGame() throws TicTacException {
        PlayerDTO player1= new PlayerDTO();
        player1.setName("javier");
        player1.setSimbol('X');

        PlayerDTO player2= new PlayerDTO();
        player2.setName("natalia");
        player2.setSimbol('O');

        gameService.startGame(player1,player2);
    }
}
