package com.bnpparidas.tictactoe.service;

import com.bnpparidas.tictactoe.dto.MovementDTO;
import com.bnpparidas.tictactoe.dto.MovementResponseDTO;
import com.bnpparidas.tictactoe.dto.PlayerDTO;
import com.bnpparidas.tictactoe.dto.ResponseGameDTO;
import com.bnpparidas.tictactoe.exception.TicTacException;

public interface GameService {

    public ResponseGameDTO startGame(PlayerDTO player1, PlayerDTO player2) throws TicTacException;

    public MovementResponseDTO makeMovement(MovementDTO movementDTO) throws TicTacException;

    public boolean hasGameStarted();

}
