package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.MovementResponseDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import com.bnpparidas.tictactoe.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovementControllerTest {

    @InjectMocks
    private MovementController movementController;

    @Mock
    private GameService gameService;

    @Test
    public void startMovementSuccessfullyTest() throws TicTacException {

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(1);
        positionDTO.setColPosition(1);

        movementDTO.setPosition(positionDTO);


        Character[][]dashboard = new Character[][]{
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        MovementResponseDTO movementResponseDTO = new MovementResponseDTO();
        movementResponseDTO.setDashboard(dashboard);
        movementResponseDTO.setWinner(false);
        movementResponseDTO.setMessage("Game Continues!!");

        when(gameService.makeMovement(movementDTO)).thenReturn(movementResponseDTO);

        ResponseEntity responseEntity=movementController.play(movementDTO);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }

    @Test
    public void makeUnallawoedPositionMovementTest(){

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setColPosition(90);
        positionDTO.setColPosition(90);

        movementDTO.setPosition(positionDTO);

        ResponseEntity responseEntity=movementController.play(movementDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());

    }

    @Test
    public void makeUnallawoedUsedPositionMovementTest(){

        MovementDTO movementDTO1 = new MovementDTO();
        movementDTO1.setSimbol('X');

        PositionDTO positionDTO1 = new PositionDTO();
        positionDTO1.setColPosition(1);
        positionDTO1.setColPosition(1);

        movementDTO1.setPosition(positionDTO1);

        ResponseEntity responseEntity1 =movementController.play(movementDTO1);


        MovementDTO movementDTO2 = new MovementDTO();
        movementDTO2.setSimbol('X');

        PositionDTO positionDTO2 = new PositionDTO();
        positionDTO2.setColPosition(1);
        positionDTO2.setColPosition(1);

        movementDTO2.setPosition(positionDTO2);

        ResponseEntity responseEntity2 =movementController.play(movementDTO2);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity2.getStatusCode());

    }

    @Test
    public void samePlayerCannotMoveTwoFollowedTimesTest(){

        MovementDTO movementDTO1 = new MovementDTO();
        movementDTO1.setSimbol('X');

        PositionDTO positionDTO1 = new PositionDTO();
        positionDTO1.setColPosition(1);
        positionDTO1.setColPosition(1);

        movementDTO1.setPosition(positionDTO1);

        ResponseEntity responseEntity1 =movementController.play(movementDTO1);

        PositionDTO positionDTO2 = new PositionDTO();
        positionDTO2.setColPosition(1);
        positionDTO2.setColPosition(2);

        movementDTO1.setPosition(positionDTO2);

        ResponseEntity responseEntity2 =movementController.play(movementDTO1);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity2.getStatusCode());

    }
}
