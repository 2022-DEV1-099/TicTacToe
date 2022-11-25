package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MovementControllerTest {

    @InjectMocks
    private MovementController movementController;

    @Test
    public void startMovementSuccessfullyTest(){

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setColPosition(1);
        positionDTO.setColPosition(1);

        movementDTO.setPosition(positionDTO);

        ResponseEntity responseEntity=movementController.movement(movementDTO);

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

        ResponseEntity responseEntity=movementController.movement(movementDTO);

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

        ResponseEntity responseEntity1 =movementController.movement(movementDTO1);


        MovementDTO movementDTO2 = new MovementDTO();
        movementDTO2.setSimbol('X');

        PositionDTO positionDTO2 = new PositionDTO();
        positionDTO2.setColPosition(1);
        positionDTO2.setColPosition(1);

        movementDTO2.setPosition(positionDTO2);

        ResponseEntity responseEntity2 =movementController.movement(movementDTO2);

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

        ResponseEntity responseEntity1 =movementController.movement(movementDTO1);

        PositionDTO positionDTO2 = new PositionDTO();
        positionDTO2.setColPosition(1);
        positionDTO2.setColPosition(2);

        movementDTO1.setPosition(positionDTO2);

        ResponseEntity responseEntity2 =movementController.movement(movementDTO1);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity2.getStatusCode());

    }
}
