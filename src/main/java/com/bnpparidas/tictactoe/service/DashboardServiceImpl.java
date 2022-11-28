package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService{

    /**  Note:
     Helpful indices
     [0][0] , [0][2] , [0][4]
     [1][0] , [1][2] , [1][4]
     [2][0] , [2][2] , [2][4]
     **/
  // private Character[][] matrix;

    private Character[][] matrix;
    public void resetDashboard(){
        matrix = new Character[][]{
                {'_', '_', '_'},
                {'_', '_', '_'},
                {' ', ' ', ' '}
        };
    }

    public boolean makeMovement(MovementDTO movementDTO){
        return false;
    }

    public boolean isPositionAvailable(MovementDTO movementDTO){
        return true;
    }

    public Character[][] getDashboard(){
        return  this.matrix;
    }

    private boolean isWinner(){
        return false;
    }
}
