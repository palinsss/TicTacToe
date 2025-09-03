# TicTacToe

Java project implementing the classic game **Tic Tac Toe** with support for both two-player mode and single-player mode against the computer using the **Minimax algorithm**.  

## Features
- **Two-player mode**: Two human players alternate moves on a 3×3 board.
- **Single-player mode**: Play against the computer, which uses the Minimax algorithm to determine the best move.
- **Game state detection**:
  - Check if the board is full
  - Check if a player has won (3 in a row vertically, horizontally, or diagonally)
  - Handle draw conditions
- **Graphical rendering with CodeDraw**:
  - Draws the 3×3 grid
  - Displays `X` as two intersecting lines
  - Displays `O` as a circle in the correct cell
- **Configurable AI depth**: The parameter `maxDepth` controls how many moves ahead the computer simulates (up to 8).

## Implemented Methods
- `int[] minimax(char[][] gameBoard, boolean player, int depth)`  
  Recursive Minimax algorithm that searches for the best possible move.  
- `boolean checkIfFull(char[][] gameBoard)`  
  Checks if the board is full.
- `boolean checkIfWinner(char[][] gameBoard, boolean player)`  
  Checks if the given player has three in a row.
- `void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard)`  
  Renders the game board and current moves.

## Technologies
- Java 17
- IntelliJ IDEA
- CodeDraw
- Git / GitHub

## License
This project is licensed under the MIT License. 
