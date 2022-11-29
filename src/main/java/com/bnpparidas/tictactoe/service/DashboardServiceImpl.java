package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public Boolean isPositionAvailable(MovementDTO movementDTO){
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

    public Boolean isDashboardEmpty(){
        List<Character[]> result = Arrays.stream(dashboard).collect(Collectors.toList());

        Predicate<Character[]> predicate = row->{
            Boolean result2 = Arrays.stream(row).allMatch(x->x.equals('_'));
            return result2;
        };

        Boolean filters = result.stream().allMatch(predicate);
        return  filters;
    }


}
