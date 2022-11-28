package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameService{

    private PlayerDTO player1;
    private PlayerDTO player2;

    private DashboardService dashboarService;

    public GameServiceImpl(DashboardServiceImpl dashboardService){
        this.dashboarService = dashboardService;
    }

    public void startGame(PlayerDTO player1,PlayerDTO player2){

    }

    public Boolean makeMovement(MovementDTO movementDTO) throws TicTacException {
        return true;
    }

    public boolean hasGameStarted(){
        return true;
    }


    private boolean validMovementRequest(MovementDTO movementDTO) {
        return true;
    }
}
