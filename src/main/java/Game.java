import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    Color currentPlayer = Color.WHITE;
    Board board;

    void start() {
        System.out.println("Hello !!");
        board = new Board(askUserForBoardSize());
        board.toString();

        while (playRound()) {
            System.out.println("Current player for - " + currentPlayer.name());
            board.toString();
        }

        board.toString();
        System.out.println("winner is ");

    }

    public boolean playRound() {// Który gracz jest następny,oraz czy ktoś wygrał.

        boolean result = true;
        while (result) {
            if (!verifyPlayerMove()) {
                continue;
            }

            if (checkForWinner()) {
                result = false;
            }

            if (currentPlayer == Color.WHITE) {
                currentPlayer = Color.BLACK;
            } else
                currentPlayer = Color.WHITE;

            break;
        }
        return result;
    }

    public boolean verifyPlayerMove() { //Sprawdza czy jest w zakresie i czy nie jest już zajęty i wywołuje metode tryToMakeMove
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which pawn you want to move?");
        String pawnPlaceOnBoard = scanner.nextLine().strip().toLowerCase();
        if (!validateUserChoosenPawnCoordinates(pawnPlaceOnBoard)) {
            System.out.println("Wrong coordinates , try again");
            return false;
        }
        int[] pawnCoordinates = stringToCoordinates(pawnPlaceOnBoard);

        System.out.println("Where you want to move it ?");
        String placeToMove = scanner.nextLine().strip().toLowerCase();
        if (!validateUserChoosenMoveCoordinates(placeToMove)) {
            System.out.println("Wrong coordinates , try again");
            return false;
        }
        int[] moveCoordinates = stringToCoordinates(placeToMove);
        System.out.println(pawnCoordinates[0] + "   " + pawnCoordinates[1]);

        if (!board.fields[pawnCoordinates[0]][pawnCoordinates[1]].tryToMakeMove(moveCoordinates, board)) {
            System.out.println("Wrong move!");
            return false;
        }

        return true;
    }

    public boolean checkForWinner() {//Sprawdza czy po rundzie ktoś wygrywa, oraz czy jest remis
        //Wygrana kiedy wszystkie pionki przeciwnika znikną
        //Zrobić log ostatnich zagrać
        if (this.checkIfNoEnemyPawnsOnBoard() || this.checkIfAllEnemiesPawnsBlocked()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfNoEnemyPawnsOnBoard() {
        Color enemyColor;
        if (currentPlayer == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        for (int i = 0; i < board.fields.length; i++) {
            for (int j = 0; j < board.fields[0].length; j++) {
                if (board.fields[i][j] != null && board.fields[i][j].color == enemyColor) {
                    return false;

                }
            }
        }
        return true;
    }

    public boolean checkIfAllEnemiesPawnsBlocked() {
        Color enemyColor;
        if (currentPlayer == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        for (int i = 0; i < board.fields.length; i++) {
            for (int j = 0; j < board.fields[0].length; j++) {
                if (board.fields[i][j] != null && board.fields[i][j].color == enemyColor) {
                    if (enemyColor == Color.WHITE){
                        this.isWhitePawnBlocked(i,j);
                    //} else {
                        //this.isBlackPawnBlocked(i,j);
                    }

                    }
                }
            }
        return false;
    }

    public boolean isWhitePawnBlocked(int i, int j) {
        if (i == 0) { // dodajemy dopoki nie mamy króli
            System.out.println("jest zablokowany");
            return true;
        }
        else if (this.isWhitePawnBlockedToTheLeft(i, j) && this.isWhitePawnBlockedToTheRight(i, j)) {
            System.out.println("jest zablokowany");
            return true;

        }
        System.out.println("nie sa zablokowane");
        return false;
    }

    public boolean isWhitePawnBlockedToTheLeft(int i, int j) {
        System.out.println("sprawdzanie");
        if (j == 0) {
            return true;
        } else if (i>=1 && board.fields[i - 1][j - 1].color == Color.WHITE) {
            return true;
        } else if (j == 1 && board.fields[i - 1][j - 1] != null) {
            return true;
        } else if (i >= 2 && board.fields[i - 1][j - 1] != null && board.fields[i - 2][j - 2] != null) {
            return true;
        } else if (i == 1 && board.fields[i-1][j-1] != null){
            return true;
        } else {
            return false;
        }
    }

    public boolean isWhitePawnBlockedToTheRight(int i, int j){
        if (j == board.fields[0].length-1) {
            return true;
        } else if (i>=1 && board.fields[i - 1][j + 1].color == Color.WHITE) {
            return true;
        } else if (j == board.fields.length-2 && board.fields[i - 1][j + 1] != null) {
            return true;
        } else if (i >= 2 && board.fields[i - 1][j + 1] != null && board.fields[i - 2][j + 2] != null) {
            return true;
        } else if (i == 1 && board.fields[i-1][j+1] != null){
            return true;
        } else {
            return false;
        }
    }



    public boolean isBlackPawnBlocked(int i, int j){
        if (this.isWhitePawnBlockedToTheLeft(i, j) && this.isWhitePawnBlockedToTheRight(i, j)) {
            return true;
        }
        return false;
        }


    public boolean isBlackPawnBlockedToTheLeft(){
        return false;
    }

    public boolean isBlackPawnBlockedToTheRight(){
        return  false;
    }

        private int askUserForBoardSize () {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("What size do you want to play on ? ");
                String size = scanner.nextLine().strip();
                if (validateUserChoicePattern(size)) {
                    int boardSize = Integer.parseInt(size);
                    if (boardSize >= 10 && boardSize <= 20 && boardSize % 2 == 0)
                        return boardSize;
                    else
                        System.out.println("The board size entered is incorrect, try between 10 and 20 (only even numbers)");
                    continue;
                } else
                    System.out.println("The input format incorrect, try numbers between 10 and 20 (only even numbers)");
                continue;
            }
        }

        private boolean validateUserChoicePattern (String userChoice){
            Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
            return pattern.matcher(userChoice).matches();
        }

        //validacja regexa + czy na polu jest pionek gracza + czy koordynat na boardzie
        private boolean validateUserChoosenPawnCoordinates (String coordinates){
            int[] pawnPlace = stringToCoordinates(coordinates);
            if (board.getFields()[pawnPlace[0]][pawnPlace[1]] == null) {
                System.out.println("blad");
                return false;
            }
            return true;
        }

        //validacja regexa + czy koordynat na boardzie
        private boolean validateUserChoosenMoveCoordinates (String coordinates){
            return true;
        }

        private int[] stringToCoordinates (String coordinates){
            char charRow = coordinates.charAt(0);
            int row = charRow - 97;
            int col = Integer.parseInt(coordinates.substring(1)) - 1;

            return new int[]{col, row};
        }
    }
