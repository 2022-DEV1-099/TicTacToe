package com.bnpparidas.tictactoe.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(chain = true)
@ConfigurationProperties("com.bnpparidas.tictactoe.messager")
public class MessagerHandler {
    private String incorrectInput;
    private String gameStarted;
    private String gameNotStarted;
    private String gameFinished;
    private String startError;
    private String winnerMessage;
    private String drawMessage;
    private String gameContinue;
    private String positionNotAvailable;
}
