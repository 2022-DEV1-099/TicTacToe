package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;

public interface DashboardService {

    public void resetDashboard();

    public void makeMovement(MovementDTO movementDTO);

    public Character[][] getDashboard();

    public boolean isPositionAvailable(MovementDTO movementDTO);
}
