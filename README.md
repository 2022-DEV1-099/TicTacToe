# Tic Tac Toe


## About this Project

The goal of the project is perform the TicTacToe game, down below are the rules in order to play the game.
## Rules

The rules are described below :

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
    - One player has three in a row, horizontally, vertically or diagonally
    - All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

  ```  Note:
  Helpful indices
  [0][0] , [0][1] , [0][2]
  [1][0] , [1][1] , [1][2]
  [2][0] , [2][1] , [2][2]
  ```

## Enpoints
- POST http://localhost:8080/api/game/startGame
In order to start the game this is the first endpoind to invoke, otherwise the game will not start. This can be used to reestart the game as well

RequestBody
```
{
    "player1DTO": {
                        "name":"javier",
                        "simbol":"X"
                },
    "player2DTO": {
                        "name":"maria",
                        "simbol":"O"
                }
}
```

ResponseBody
```
{
    "message": "Game has started Successfully",
    "dashboard": [
        [
            "_",
            "_",
            "_"
        ],
        [
            "_",
            "_",
            "_"
        ],
        [
            "_",
            "_",
            "_"
        ]
    ]
}
```


- POST http://localhost:8080/api/movement/play
  This endpoint will place the symbol in the chosen position, as long is available and the game continues

RequestBody
```

{
    "simbol": "X",
    "position": {
                        "rowPosition":2, 
                        "colPosition":0
                }

}
```


ResponseBody
```
{
    "message": "You are the Winner,Game Finished!!",
    "dashboard": [
        [
            "X",
            "O",
            "O"
        ],
        [
            "X",
            "_",
            "_"
        ],
        [
            "X",
            "_",
            "_"
        ]
    ],
    "winner": true
}
```

## Running The Project
To run the project open a terminal and run the following sentences in the beginning of the directory(TicTacToe folder).
The project will run under the port 8080 and the address localhost.

first run this command line, to build and download dependencies
`mvn clean install`

After that run the followin command to run the project 
`mvn spring-boot:run`
