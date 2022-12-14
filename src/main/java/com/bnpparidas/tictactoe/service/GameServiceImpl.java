package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.util.MessagerHandler;
import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.MovementResponseDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.dto.ResponseGameDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameServiceImpl implements GameService{

    private PlayerDTO player1;
    private PlayerDTO player2;
    private DashboardService dashboarService;
    private Character lastPlayer;

    private MessagerHandler messagerHandler;

    public GameServiceImpl(DashboardServiceImpl dashboardService,MessagerHandler messagerHandler){
        this.dashboarService = dashboardService;
        this.messagerHandler= messagerHandler;
    }

    public ResponseGameDTO startGame(PlayerDTO player1, PlayerDTO player2) throws TicTacException {

        if(!validStartGameRequest(player1, player2)){
            throw new TicTacException(messagerHandler.getIncorrectInput());
        }
        this.player1= player1;
        this.player2= player2;
        this.dashboarService.resetDashboard();
        this.lastPlayer='O';
        ResponseGameDTO responseGameDTO = new ResponseGameDTO();
        responseGameDTO.setDashboard(dashboarService.getDashboard());
        responseGameDTO.setMessage(messagerHandler.getGameStarted());
        return responseGameDTO;
    }

    public MovementResponseDTO makeMovement(MovementDTO movementDTO) throws TicTacException {

        MovementResponseDTO movementResponseDTO = new MovementResponseDTO();

        if(!hasGameStarted()){
            throw new TicTacException(messagerHandler.getGameNotStarted());
        }

        if(hasGameFinished()){
            throw new TicTacException(messagerHandler.getGameFinished());
        }

        if(!validMovementRequest(movementDTO)){
            throw new TicTacException(messagerHandler.getIncorrectInput());
        }

        if(hasGameStarted() && dashboarService.getDashboard().length==0 && movementDTO.getSimbol().equals('O')){
            throw  new TicTacException(this.messagerHandler.getStartError());
        }

        if(dashboarService.isPositionAvailable(movementDTO)){
            this.dashboarService.makeMovement(movementDTO);
            lastPlayer=movementDTO.getSimbol();
            movementResponseDTO.setDashboard(dashboarService.getDashboard());
            movementResponseDTO.setWinner(isWinner(movementDTO.getSimbol()));
            if(movementResponseDTO.getWinner()){
                 movementResponseDTO.setMessage(messagerHandler.getWinnerMessage());
            }else{

                if(!movementResponseDTO.getWinner() && dashboardCompleted()){
                    movementResponseDTO.setMessage(messagerHandler.getDrawMessage());
                }else{
                    movementResponseDTO.setMessage(messagerHandler.getGameContinue());
                }
            }
            return movementResponseDTO;
        }else{
            throw  new TicTacException(messagerHandler.getPositionNotAvailable());
        }
    }

    private boolean dashboardCompleted() {
        Character[][] dashboard = this.dashboarService.getDashboard();

        List<Character[]> rowList = Arrays.stream(dashboard).collect(Collectors.toList());

        Predicate<Character[]> predicateRow = row->{
            Boolean result = Arrays.stream(row).anyMatch(x->x.equals('_'));
            return result;
        };

        boolean result= rowList.stream().anyMatch(predicateRow);
        return !result;
    }

    public boolean hasGameStarted(){
        if(player1==null && player2==null){
            return false;
        }
        return true;
    }

    public Character[][] getDashboard(){
        return this.dashboarService.getDashboard();
    }

    private boolean hasGameFinished() {
        return isWinner('X') || isWinner('O') || dashboardCompleted();
    }

    private  Boolean isWinner(Character symbol){
        List<Character[]> rowList = Arrays.stream(this.dashboarService.getDashboard()).collect(Collectors.toList());
        Predicate<Character[]> predicateRow = row->{
            Boolean result = Arrays.stream(row).allMatch(x->x.equals(symbol));
            return result;
        };
        if(rowList.stream().anyMatch(predicateRow)){
            return true;
        }

        List<Character> firstColumn = Arrays.stream(this.dashboarService.getDashboard()).map(x -> x[0]).collect(Collectors.toList());
        List<Character> secondColumn = Arrays.stream(this.dashboarService.getDashboard()).map(x -> x[1]).collect(Collectors.toList());
        List<Character> thridColum = Arrays.stream(this.dashboarService.getDashboard()).map(x -> x[2]).collect(Collectors.toList());

        List<List<Character>> colList = Arrays.asList(firstColumn,secondColumn,thridColum);

        Predicate<List<Character>> predicateCol = col->{
            Boolean match = col.stream().allMatch(x->x.equals(symbol));
            return match;
        };

        if(colList.stream().anyMatch(predicateCol)){
            return true;
        }

        Character[][] dashboard = this.dashboarService.getDashboard();
        if(dashboard[0][0].equals(dashboard[1][1]) && dashboard[1][1].equals(symbol)){
            return dashboard[2][2].equals(symbol);
        }

        if(dashboard[0][2].equals(dashboard[1][1]) && dashboard[1][1].equals(symbol)){
            return dashboard[2][0].equals(symbol);
        }
        return false;
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
