/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Game "TicTacToe"
*/

import codedraw.*;

public class Aufgabe1 {
    public static void main(String[] args) {

        int size = 600;
        CodeDraw myDrawObj = new CodeDraw(size, size);
        myDrawObj.setTitle("Tic Tac Toe");
        EventScanner myEventSC = myDrawObj.getEventScanner();

        char[][] gameBoard = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean twoPlayer = false; //true ... human vs. human, false ... human vs. computer
        boolean player = true; //(1Player) human = true, computer = false, (2Player) human1 = true, human2 = false
        int maxDepth = 4;
        boolean gameRunning = true;

        drawGameBoard(myDrawObj, gameBoard);

        while (!myDrawObj.isClosed() && gameRunning) {
            if (myEventSC.hasMouseClickEvent()) {
                // TODO: Implementieren Sie hier Ihre Lösung für den if-Zweig
                MouseClickEvent currentClick = myEventSC.nextMouseClickEvent();
                int oneThird = size/3;
                int mouseX = currentClick.getX() / oneThird;
                int mouseY = currentClick.getY() / oneThird;


                if (gameBoard[mouseY][mouseX] == ' '){
                    gameBoard[mouseY][mouseX] = player ? 'X' : 'O';


                    drawGameBoard(myDrawObj, gameBoard);

                    if (checkIfWinner(gameBoard, player)) {
                        System.out.println((player ? "X player" : "O player") + " wins");
                        gameRunning = false;
                    } else if (checkIfFull(gameBoard)) {
                        System.out.println("It's a draw");
                        gameRunning = false;
                    }
                    player = !player;
                }

            } else if (!twoPlayer && !player) {
                // TODO: Implementieren Sie hier Ihre Lösung für den else-if-Zweig
                int[]move = minimax(gameBoard, false, maxDepth);
                gameBoard[move[0]][move[1]] = 'O';
                player = true;                               // Person ist dran

                drawGameBoard(myDrawObj, gameBoard);

                if (checkIfWinner(gameBoard, false)) {
                    System.out.println("Computer wins");
                    gameRunning = false;
                } else if (checkIfFull(gameBoard)) {
                    System.out.println("It's a draw");
                    gameRunning = false;
                }

            } else {
                myEventSC.nextEvent();
            }
        }
    }

    private static int[] minimax(char[][] gameBoard, boolean player, int depth) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[]retArray = new int[3];
        if (player){
            retArray[2] = Integer.MAX_VALUE;
        } else {
            retArray[2] = Integer.MIN_VALUE;
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == ' '){
                    if (player){
                        gameBoard[row][col] = 'X';
                    } else {
                        gameBoard[row][col] = 'O';
                    }
                    if (checkIfWinner(gameBoard, player)){
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = -1;
                    } else if (checkIfWinner(gameBoard, !player)){
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 1;
                    } else if (checkIfFull(gameBoard) || depth-1 == 0){
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 0;
                    } else {
                        int[] tempArray = minimax(gameBoard, !player, depth-1);
                        if (player) {
                            if (tempArray[2] < retArray[2]) {
                                retArray[0] = row;
                                retArray[1] = col;
                                retArray[2] = tempArray[2];
                            }
                        } else {
                            if (tempArray[2] > retArray[2]) {
                                retArray[0] = row;
                                retArray[1] = col;
                                retArray[2] = tempArray[2];
                            }
                        }
                    }
                    gameBoard[row][col] = ' ';
                }
            }
        }
        return retArray; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfWinner(char[][] gameBoard, boolean player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        char character = ' ';
        if (player){
            character = 'X';
        } else {
            character = 'O';
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] &&  gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][1] == character){
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == gameBoard[1][i] &&  gameBoard[1][i] == gameBoard[2][i] && gameBoard[1][i] == character){
                return true;
            }
        }
        if (gameBoard[0][0]==gameBoard[1][1] && gameBoard[1][1]==gameBoard[2][2] && gameBoard[2][2] == character) {
            return true;
        }
        if (gameBoard[0][2]==gameBoard[1][1] && gameBoard[1][1]==gameBoard[2][0] && gameBoard[2][0] == character) {
            return true;
        }
        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int width = myDrawObj.getWidth();
        int height = myDrawObj.getHeight();
        // Trennlinien
        myDrawObj.drawLine(width/3., 0, width/3., height);
        myDrawObj.drawLine(2 * width/3., 0, 2 * width/3., height);
        myDrawObj.drawLine(0, height/3., width, height/3.);
        myDrawObj.drawLine(0, 2 * height/3., width, 2 * height/3.);

        double oneThirdWidth = width / 3.;
        double oneThirdHeight = height / 3.;
        double radius = oneThirdWidth / 2.;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == 'X'){
                    myDrawObj.drawLine(col * oneThirdWidth, row * oneThirdHeight,
                            (col + 1) * oneThirdWidth, (row + 1) * oneThirdHeight);
                    myDrawObj.drawLine((col + 1) * oneThirdWidth, row * oneThirdHeight,
                            col * oneThirdWidth, (row + 1) * oneThirdHeight);
                }
                if (gameBoard[row][col] == 'O'){
                    myDrawObj.drawCircle(col * oneThirdWidth + radius, row * oneThirdHeight + radius, radius);
                }
            }
        }
        myDrawObj.show();
    }

}
