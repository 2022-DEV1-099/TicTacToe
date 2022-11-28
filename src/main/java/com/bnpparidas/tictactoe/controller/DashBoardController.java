package com.bnpparidas.tictactoe.controller;

import com.bnpparidas.tictactoe.service.DashboardService;
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

    private DashboardService dashboardService;

    public DashBoardController(DashboardService dashboardService){
        this.dashboardService=dashboardService;
    }
    @GetMapping("/consult")
    public ResponseEntity getDashboardState() {

        log.info("helloWorld:" + dashboardService.getDashboard());
        return ResponseEntity.status(HttpStatus.CREATED).body(dashboardService.getDashboard());
    }

}
