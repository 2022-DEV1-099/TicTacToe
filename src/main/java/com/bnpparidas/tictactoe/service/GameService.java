package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;

public interface GameService {

    public void startGame(PlayerDTO player1, PlayerDTO player2) throws TicTacException;

    public Boolean makeMovement(MovementDTO movementDTO) throws TicTacException;

    public boolean hasGameStarted();

}
