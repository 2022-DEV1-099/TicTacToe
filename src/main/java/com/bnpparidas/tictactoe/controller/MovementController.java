package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.dto.ErrorResponseDTO;
import com.bnpparidas.tictactoe.dto.MovementDTO;
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

    public MovementController( GameService gameService){
        this.gameService=gameService;
    }
    @PostMapping("/play")
    public ResponseEntity play(@RequestBody MovementDTO movementDTO) {

        log.info("m=play, simbol:{} , colPosition:{}, rowPosition:{}", movementDTO.getSimbol(),
                movementDTO.getPosition().getColPosition(), movementDTO.getPosition().getRowPosition());

        if(!validMovementRequest(movementDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Input values are not valid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        try{
            boolean isWinner = gameService.makeMovement(movementDTO);
            if(isWinner){
                return ResponseEntity.status(HttpStatus.OK).body("You are the Winner,Game Finished!!");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("Movement successfully Done");
            }
        }catch(Exception e){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());
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
