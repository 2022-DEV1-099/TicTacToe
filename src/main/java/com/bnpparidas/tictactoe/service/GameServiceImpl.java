package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class GameServiceImpl implements GameService{

    private PlayerDTO player1;
    private PlayerDTO player2;

    private DashboardService dashboarService;

    private Character lastPlayer;

    public GameServiceImpl(DashboardServiceImpl dashboardService){
        this.dashboarService = dashboardService;
    }

    public void startGame(PlayerDTO player1,PlayerDTO player2) throws TicTacException {

        if(!validStartGameRequest(player1, player2)){
            throw new TicTacException("Input Data is not Valid");
        }
        this.player1= player1;
        this.player2= player2;
        this.dashboarService.resetDashboard();
        this.lastPlayer='O';
    }

    private Boolean validStartGameRequest(PlayerDTO player1, PlayerDTO player2) {

        if(player1==null || player2==null){
            return false;
        }

        if(player1.getSimbol()==null || player2.getSimbol()==null){
            return false;
        }

        if(player1.getSimbol().equals(player2.getSimbol())){
            return false;
        }

        if(player1.getSimbol().equals('O') && player2.getSimbol().equals('X')){
            return true;
        }

        if(player1.getSimbol().equals('X') && player2.getSimbol().equals('O')){
            return true;
        }
        return false;
    }

    public Boolean makeMovement(MovementDTO movementDTO) throws TicTacException {

        Boolean isWinner= false;

        if(!hasGameStarted()){
            throw new TicTacException("Invalid Movement Game didnt Start");
        }

        if(!validMovementRequest(movementDTO)){
            throw new TicTacException("Input Data is not Valid");
        }


        if(hasGameStarted() && dashboarService.getDashboard().length==0 && movementDTO.getSimbol().equals('O')){
            throw  new TicTacException("X move First");
        }
        if(dashboarService.isPositionAvailable(movementDTO)){
             this.dashboarService.makeMovement(movementDTO);
             lastPlayer=movementDTO.getSimbol();
             return isWinner;
        }else{
            throw  new TicTacException("Position is not Available");
        }
    }

    public boolean hasGameStarted(){
        if(player1==null && player2==null){
            return false;
        }
        return true;
    }


    private boolean validMovementRequest(MovementDTO movementDTO) {
        if(!movementDTO.getSimbol().equals('X') && !movementDTO.getSimbol().equals('O')){
            return false;
        }

        if(movementDTO.getPosition().getColPosition()>2 || movementDTO.getPosition().getColPosition()<0 ){
            return false;
        }

        if(movementDTO.getPosition().getRowPosition()>2 || movementDTO.getPosition().getRowPosition()<0 ){
            return false;
        }

        if(this.dashboarService.isDashboardEmpty() && movementDTO.getSimbol().equals('O')){
            return false;
        }

        if(this.lastPlayer.equals(movementDTO.getSimbol())){
            return false;
        }
        return true;
    }
}
