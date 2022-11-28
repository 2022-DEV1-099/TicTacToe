package com.bnpparidas.tictactoe.controller;


import com.bnpparidas.tictactoe.dto.ErrorResponseDTO;
import com.bnpparidas.tictactoe.dto.GameDTO;
import com.bnpparidas.tictactoe.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService=gameService;
    }

    @PostMapping("/startGame")
    public ResponseEntity startGame(@RequestBody GameDTO gameDTO) {

        log.info("m=startGame:"+gameDTO);

        if(!validGameRequest(gameDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Input values are not valid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        try{
            gameService.startGame(gameDTO.getPlayer1DTO(), gameDTO.getPlayer2DTO());
            return ResponseEntity.status(HttpStatus.OK).body("Game successfully Started");
        }catch (Exception e){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }
    }

    private boolean validGameRequest(GameDTO gameDTO) {
        if(gameDTO==null || gameDTO.getPlayer1DTO()==null || gameDTO.getPlayer2DTO()==null){
            return false;
        }

        if(gameDTO.getPlayer1DTO().getSimbol()==null|| gameDTO.getPlayer2DTO().getSimbol()==null){
            return false;
        }

        if(StringUtils.isEmpty(gameDTO.getPlayer1DTO().getSimbol().toString())|| StringUtils.isEmpty(gameDTO.getPlayer2DTO().getSimbol().toString())){
            return false;
        }

        if(gameDTO.getPlayer1DTO().getName()==null|| gameDTO.getPlayer2DTO().getName()==null){
            return false;
        }

        if(StringUtils.isEmpty(gameDTO.getPlayer1DTO().getName())|| StringUtils.isEmpty(gameDTO.getPlayer2DTO().getName())){
            return false;
        }
        return true;
    }
}
