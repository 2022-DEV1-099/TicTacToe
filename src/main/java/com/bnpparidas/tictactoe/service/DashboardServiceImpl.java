package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
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

    private Character[][] dashboard;
    public void resetDashboard(){
        dashboard = new Character[][]{
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
    }

    public void makeMovement(MovementDTO movementDTO){
        int col= movementDTO.getPosition().getColPosition();
        int row = movementDTO.getPosition().getRowPosition();
        dashboard[row][col] = movementDTO.getSimbol();
    }

    public boolean isPositionAvailable(MovementDTO movementDTO){
        PositionDTO position = movementDTO.getPosition();
        if((dashboard[position.getRowPosition()][position.getColPosition()]).equals('_')){
            return true;
        }else{
            return false;
        }
    }

    public Character[][] getDashboard(){
        return  this.dashboard;
    }

}
