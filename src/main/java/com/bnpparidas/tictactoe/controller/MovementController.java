package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.dto.MovementDTO;
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

    @PostMapping("/movement")
    public ResponseEntity movement(@RequestBody MovementDTO movementDTO) {
        log.info("StartingGame:"+movementDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Movement successfully Done");
    }

}
