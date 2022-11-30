package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.util.MessagerHandler;
import com.bnpparidas.tictactoe.dto.ErrorResponseDTO;
import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.MovementResponseDTO;
import com.bnpparidas.tictactoe.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/movement")
public class MovementController {

    private GameService gameService;

    private MessagerHandler messagerHandler;

    public MovementController( GameService gameService,MessagerHandler messagerHandler){
        this.gameService=gameService;
        this.messagerHandler =messagerHandler;
    }
    @PostMapping("/play")
    public ResponseEntity play(@RequestBody MovementDTO movementDTO) {

        if(!validMovementRequest(movementDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(messagerHandler.getIncorrectInput());
            errorResponseDTO.setDashboard(this.gameService.getDashboard());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        try{
            MovementResponseDTO movementResponseDTO = gameService.makeMovement(movementDTO);
            return ResponseEntity.status(HttpStatus.OK).body(movementResponseDTO);
        }catch(Exception e){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());
            errorResponseDTO.setDashboard(this.gameService.getDashboard());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }
    }

    private boolean validMovementRequest(MovementDTO movementDTO) {
        if(movementDTO==null || movementDTO.getSimbol()== null || movementDTO.getPosition()== null){
            return false;
        }

        if(movementDTO.getPosition().getRowPosition() == null || movementDTO.getPosition().getColPosition() == null ){
            return false;
        }
        return true;
    }

}
