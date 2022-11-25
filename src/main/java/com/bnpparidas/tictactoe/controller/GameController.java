package com.bnpparidas.tictactoe.controller;


import com.bnpparidas.tictactoe.dto.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/game")
public class GameController {

    @PostMapping("/startGame")
    public ResponseEntity startGame(@RequestBody GameDTO gameDTO) {
        log.info("StartingGame:"+gameDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Game successfully Started");
    }
}
