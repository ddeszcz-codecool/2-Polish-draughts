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
        System.out.println("Current player for - " + currentPlayer.name());
        while (playRound()) {
            System.out.println("Current player for - " + currentPlayer.name());
            board.toString();
        }

        board.toString();
        System.out.println("The Winner is " + currentPlayer); //Do edycji

    }

    public boolean playRound() {// Który gracz jest następny,oraz czy ktoś wygrał.

        boolean result = true;
        while (result) {
            if (!verifyPlayerMove()) {
                continue;
            }

            if (checkForWinner()) {
                result = false;
                break;
            }

            if (currentPlayer == Color.WHITE) {
                currentPlayer = Color.BLACK;
            } else currentPlayer = Color.WHITE;

            break;
        }
        return result;
    }

    public boolean verifyPlayerMove() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which pawn do you want to move?");
        String pawnPlaceOnBoard = scanner.nextLine().strip().toLowerCase();
        if (!validateCoordinatesAreOnlyLetterAndNumber(pawnPlaceOnBoard)) {
            System.out.println("Your coordinates are not correct, please use a following format - letter + number - e.g. A2, B4");
            return false;
        }
        int[] pawnCoordinates = stringToCoordinates(pawnPlaceOnBoard);
        if (!validateCoordinatesAreInRange(pawnCoordinates)) {
            int boardWidth = board.fields.length;
            char boardHeight = (char) (board.fields.length + 64);
            System.out.println("Your coordinates are out of range please use coordinates from 1 to " + boardWidth + " and from A to " + boardHeight);
            return false;
        }
        if (!validateUserChosenPawnNotNullAndNotDifferentColor(pawnCoordinates)) {
            return false;
        }


        System.out.println("Where you want to move it ?");
        String placeToMove = scanner.nextLine().strip().toLowerCase();
        if (!validateCoordinatesAreOnlyLetterAndNumber(placeToMove)) {
            System.out.println("Your coordinates are not correct, please use format letter plus number e.g. A2, B4");
            return false;
        }
        int[] moveCoordinates = stringToCoordinates(placeToMove);
        if (!validateCoordinatesAreInRange(moveCoordinates)) {
            int boardWidth = board.fields.length;
            char boardHeight = (char) (board.fields.length + 64);
            System.out.println("Your coordinates are out of range please use coordinates from 1 to " + boardWidth + " and from A to " + boardHeight);
            return false;
        }
        if (!validateUserChosenMoveCoordinatesAreNull(moveCoordinates)) {
            return false;
        }
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
        int counterP = 0;
        int counter = 0;
        if (currentPlayer == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        for (int i = 0; i < board.fields.length; i++) {
            for (int j = 0; j < board.fields[0].length; j++) {
                if (board.fields[i][j] != null && board.fields[i][j].color == enemyColor) {
                    counterP++;
                    System.out.println(counterP + " P");
                    if (enemyColor == Color.WHITE) {
                        if (!this.isWhitePawnBlocked(i, j)) {
                            return false;
                        } else {
                            counter++;
                            System.out.println(counter + " C");
                        }
                    } else {
                        if (!this.isBlackPawnBlocked(i, j)) {
                            return false;
                        } else {
                            counter++;
                            System.out.println(counter + " C");
                        }
                    }
                }
            }
        }
        System.out.println(" " + counter + counterP);
        if (counter == counterP) return true;
        return false;
    }

    public boolean isWhitePawnBlocked(int i, int j) {
        if (i == 0) { // dodajemy dopoki nie mamy króli
            System.out.println("jest zablokowany1");
            return true;
        } else if (this.isWhitePawnBlockedToTheLeft(i, j) && this.isWhitePawnBlockedToTheRight(i, j)) {
            System.out.println("jest zablokowany2");
            return true;

        }
        System.out.println("nie sa zablokowane");
        return false;
    }

//    public boolean isWhitePawnBlockedToTheLeft(int i, int j) {
//        System.out.println("sprawdzanie");
//        if (j == 0) {
//            return true;
//        } else if ( i>=1 && board.fields[i - 1][j - 1].color == Color.WHITE) {
//            return true;
//        } else if (j == 1 && board.fields[i - 1][j - 1] != null) {
//            return true;
//        } else if (i >= 2 && board.fields[i - 1][j - 1] != null && board.fields[i - 2][j - 2] != null) {
//            return true;
//        } else if (i == 1 && board.fields[i - 1][j - 1] != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean isWhitePawnBlockedToTheLeft(int row, int col) {
        System.out.println("sprawdzanie");
        if (col == 0) return true;
        else if (row > 0 && board.fields[row - 1][col - 1] != null) {
            if (board.fields[row - 1][col - 1].color == Color.BLACK) {
                if (col > 1 && row > 1 && board.fields[row - 2][col - 2] != null)
                else return false;
            } else if (row < board.fields.length - 1 && board.fields[row + 1][col - 1] != null) {
                if (board.fields[row + 1][col - 1].color == Color.BLACK) {
                    if (col > 1 && row < board.fields.length - 2 && board.fields[row + 2][col - 2] != null) return true;
                }
                return true;
            }
        }
        return false;
    }

//    public boolean isWhitePawnBlockedToTheRight(int i, int j) {
//        if (j == board.fields[0].length - 1) {
//            return true;
//        } else if (i >= 1 && board.fields[i - 1][j + 1].color == Color.WHITE) {
//            return true;
//        } else if (j == board.fields.length - 2 && board.fields[i - 1][j + 1] != null) {
//            return true;
//        } else if (i >= 2 && board.fields[i - 1][j + 1] != null && board.fields[i - 2][j + 2] != null) {
//            return true;
//        } else if (i == 1 && board.fields[i - 1][j + 1] != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean isWhitePawnBlockedToTheRight(int row, int col) {
        System.out.println("sprawdzanie");
        if (col == board.fields[0].length - 1) return true;
        else if (row > 0 && board.fields[row - 1][col + 1] != null) {
            if (board.fields[row - 1][col + 1].color == Color.BLACK) {
                if (col < board.fields.length - 2 && row > 1 && board.fields[row - 2][col + 2] != null) return true;
                else return false;
            } else return true;
        } else if (row < board.fields.length - 1 && board.fields[row + 1][col + 1] != null) {
            if (board.fields[row + 1][col + 1].color == Color.BLACK) {
                if (col < board.fields.length - 2 && row < board.fields.length - 2 && board.fields[row + 2][col + 2] != null)
                    return true;
            }
            return true;
        }
        return false;
    }

    public boolean isBlackPawnBlocked(int i, int j) {
        if (i == board.fields[0].length - 1) { // dodajemy dopoki nie mamy króli
            System.out.println("jest zablokowany1");
            return true;
        } else if (this.isBlackPawnBlockedToTheLeft(i, j) && this.isBlackPawnBlockedToTheRight(i, j)) {
            System.out.println("jest zablokowany2");
            return true;
        }
        System.out.println("nie sa zablokowane");
        return false;
    }


    public boolean isBlackPawnBlockedToTheLeft(int row, int col) {
        System.out.println("sprawdzanie");
        if (col == 0) return true;
        else if (row < board.fields[0].length - 1 && board.fields[row + 1][col - 1] != null) {
            if (board.fields[row + 1][col - 1].color == Color.WHITE) {
                if (col > 1 && row < board.fields[0].length - 2 && board.fields[row + 2][col - 2] != null)
                    return true;
                else return false;
            } else return true;
        } else if (row > 0 && board.fields[row - 1][col - 1] != null) {
            if (board.fields[row - 1][col - 1].color == Color.BLACK) {
                if (col > 1 && row > 1 && board.fields[row - 2][col - 2] != null) return true;
            }
            return true;
        }
        return false;
    }

    public boolean isBlackPawnBlockedToTheRight(int row, int col) {
        System.out.println("sprawdzanie");
        if (col == board.fields[0].length - 1) return true;
        else if (row < board.fields[0].length - 1 && board.fields[row + 1][col + 1] != null) {
            if (board.fields[row + 1][col + 1].color == Color.WHITE) {
                if (col < board.fields[0].length - 2 && row < board.fields[0].length - 2 && board.fields[row + 2][col + 2] != null)
                    return true;
                else return false;
            } else return true;
        } else if (row > 0 && board.fields[row - 1][col + 1] != null) {
            if (board.fields[row - 1][col + 1].color == Color.BLACK) {
                if (col < board.fields[0].length - 2 && row > 1 && board.fields[row - 2][col + 2] != null)
                    return true;
            }
            return true;
        }
        return false;
    }

    private int askUserForBoardSize() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What size do you want to play on ? ");
            String size = scanner.nextLine().strip();
            if (validateUserChoicePattern(size)) {
                int boardSize = Integer.parseInt(size);
                if (boardSize >= 6 && boardSize <= 20 && boardSize % 2 == 0) return boardSize;
                else
                    System.out.println("The board size entered is incorrect, try between 10 and 20 (only even numbers)");
                continue;
            } else
                System.out.println("The input format incorrect, try numbers between 10 and 20 (only even numbers)");
            continue;
        }
    }

    private boolean validateUserChoicePattern(String userChoice) {
        Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
        return pattern.matcher(userChoice).matches();
    }


    private boolean validateUserChosenPawnNotNullAndNotDifferentColor(int[] pawnPlace) {
        if (board.getFields()[pawnPlace[0]][pawnPlace[1]] == null) {
            System.out.println("There is no pawn on this field");
            return false;
        }

        if (currentPlayer != board.getFields()[pawnPlace[0]][pawnPlace[1]].color) {
            System.out.println("This is Yours opponent pawn, please use your pawn");
            return false;
        }
        return true;
    }

    private boolean validateUserChosenMoveCoordinatesAreNull(int[] pawnPlace) {
        if (board.getFields()[pawnPlace[0]][pawnPlace[1]] != null) {
            System.out.println("You cannot move here, this field is occupied by another pawn");
            return false;
        }
        return true;
    }

    private int[] stringToCoordinates(String coordinates) {
        char charRow = coordinates.charAt(0);
        int row = charRow - 97;
        int col = Integer.parseInt(coordinates.substring(1)) - 1;

        return new int[]{col, row};
    }

    private boolean validateCoordinatesAreOnlyLetterAndNumber(String coordinates) {
        Pattern pattern = Pattern.compile("^[a-z][0-9]{1,2}$");
        return pattern.matcher(coordinates).matches();
    }

    private boolean validateCoordinatesAreInRange(int[] coordinates) {
        return coordinates[0] < board.fields.length && coordinates[1] < board.fields.length;
    }
}
