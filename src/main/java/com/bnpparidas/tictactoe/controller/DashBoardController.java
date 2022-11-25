package com.bnpparidas.tictactoe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {

    @GetMapping("/consult")
    public ResponseEntity getDashboardState() {

        /*
        char[] firstRow = new char[3];
        char[] secondRow = new char[3];
        char[] thirdRow = new char[3];

        firstRow[0] = 'X';
        firstRow[1] = 'O';
        firstRow[2] = '-';

        secondRow[0] = '-';
        secondRow[1] = 'X';
        secondRow[2] = '-';

        thirdRow[0] = 'O';
        thirdRow[1] = 'X';
        thirdRow[2] = '-';

        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.setFirstRow(firstRow);
        dashboardDTO.setSecondRow(secondRow);
        dashboardDTO.setThirdRow(thirdRow);
*/

        log.info("helloWorld:" );
        return ResponseEntity.status(HttpStatus.CREATED).body("helloWorld:");
    }

}
