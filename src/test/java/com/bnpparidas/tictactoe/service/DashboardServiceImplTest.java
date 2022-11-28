package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class DashboardServiceImplTest {

    private DashboardService dashboardService;

    @BeforeEach
    void init() {
        dashboardService = new DashboardServiceImpl();
    }

    @Test
    public void resetDashboardSuccessfullyTest(){
        Character[][] matrix = dashboardService.getDashboard();

        Assertions.assertNull(matrix);
        dashboardService.resetDashboard();

        Character[][] matrix2 = dashboardService.getDashboard();

        Assertions.assertNotNull(matrix2);
    }

    @Test
    public void makeMovement_SuccessfullMovementTest(){

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(1);
        positionDTO.setColPosition(1);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(positionDTO);

        dashboardService.resetDashboard();
        dashboardService.makeMovement(movementDTO);

        Character value = dashboardService.getDashboard()[1][1];

        Assertions.assertEquals(movementDTO.getSimbol(),value);
    }

    @Test
    public void makeMovement_invalidPositionTest(){

        dashboardService.resetDashboard();

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(9);
        positionDTO.setColPosition(9);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(positionDTO);


        Exception exception = assertThrows(RuntimeException.class, () -> {
            dashboardService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }


    @Test
    public void makeMovement_MatrizNullTest(){

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(2);
        positionDTO.setColPosition(2);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(positionDTO);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            dashboardService.makeMovement(movementDTO);
        });

        Assertions.assertNotNull(exception);
    }

    @Test
    public void isAvailablePosition_positionAvailableTest(){

        dashboardService.resetDashboard();

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(2);
        positionDTO.setColPosition(2);

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(positionDTO);

        Boolean isVailable = dashboardService.isPositionAvailable(movementDTO);

        Assertions.assertTrue(isVailable);
    }

    @Test
    public void isAvailablePosition_positionNotAvailableTest(){

        dashboardService.resetDashboard();

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setRowPosition(1);
        positionDTO.setColPosition(1);
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setSimbol('X');
        movementDTO.setPosition(positionDTO);

        Boolean isVailable = dashboardService.isPositionAvailable(movementDTO);
        Assertions.assertTrue(isVailable);

        dashboardService.makeMovement(movementDTO);

        PositionDTO positionDTO2 = new PositionDTO();
        positionDTO2.setRowPosition(1);
        positionDTO2.setColPosition(1);
        MovementDTO movementDTO2 = new MovementDTO();
        movementDTO2.setSimbol('O');
        movementDTO2.setPosition(positionDTO2);
        Boolean isVailable2 = dashboardService.isPositionAvailable(movementDTO);
        Assertions.assertFalse(isVailable2);
    }
}
