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
            board.toString();
            System.out.println("Current player for - " + currentPlayer.name());
        }

        board.toString();
        System.out.println("The Winner is " + currentPlayer + " player !!");
    }

    public boolean playRound() {

        boolean result = true;
        while (result) {
            if (!verifyPlayerMove()) {
                continue;
            }

            if (checkForWinner()) {
                result = false;
                break;
            }
            changePlayer();


            break;
        }
        return result;
    }

    private void changePlayer() {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else currentPlayer = Color.WHITE;
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
            System.out.println("Move is not valid. Please choose again");
            return false;
        }

        return true;
    }

    public boolean checkForWinner() {
        return checkIfNoEnemyPawnsOnBoard() || checkIfAllEnemyPawnsBlocked();
    }

    public boolean checkIfNoEnemyPawnsOnBoard() {

        for (int i = 0; i < board.fields.length; i++) {
            for (int j = 0; j < board.fields[0].length; j++) {
                if (board.fields[i][j] != null && board.fields[i][j].color != currentPlayer) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkIfAllEnemyPawnsBlocked() {
        for (int row = 0; row < board.fields.length; row++) {
            for (int col = 0; col < board.fields[0].length; col++) {
                if (!isFieldEmpty(row, col) && board.fields[row][col].color != currentPlayer) {
                    if (!isPawnBlocked(row, col))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean isPawnBlocked(int row, int col) {
        int leftDirection = -1;
        int rightDirection = 1;

        return isPawnBlockedInSpecificDirection(row, col, leftDirection) &&
                isPawnBlockedInSpecificDirection(row, col, rightDirection);
    }

    private boolean isPawnBlockedInSpecificDirection(int row, int col, int enemyColDirection) {
        if (isMovePossible(row, col, enemyColDirection))
            return false;

        return !isCapturePossibleUp(row, col, enemyColDirection) &&
                !isCapturePossibleDown(row, col, enemyColDirection);
    }

    private boolean isMovePossible(int row, int col, int enemyColDirection) {
        int enemyRowDirection = currentPlayer == Color.WHITE ? 1 : -1;

        if (isIndexOutOfBounds(row + enemyRowDirection) || isIndexOutOfBounds(col + enemyColDirection))
            return false;

        return isFieldEmpty(row + enemyRowDirection, col + enemyColDirection);
    }

    private boolean isFieldEmpty(int row, int col) {
        return board.fields[row][col] == null;
    }

    private boolean isIndexOutOfBounds(int index) {
        return (index < 0 || index >= board.fields[0].length);
    }

    private boolean isCapturePossibleDown(int row, int col, int enemyColDirection) {
        int colJump = enemyColDirection * 2;
        if (isIndexOutOfBounds(col + colJump) || isIndexOutOfBounds(row + 2))
            return false;

        if (!isFieldEmpty(row + 2, col + colJump))
            return false;

        return !isFieldEmpty(row + 1, col + enemyColDirection) &&
                board.fields[row + 1][col + enemyColDirection].color == currentPlayer;
    }

    private boolean isCapturePossibleUp(int row, int col, int enemyColDirection) {
        int colJump = enemyColDirection * 2;
        if (isIndexOutOfBounds(col + colJump) || isIndexOutOfBounds(row - 2))
            return false;

        if (!isFieldEmpty(row - 2, col + colJump))
            return false;

        return !isFieldEmpty(row - 1, col + enemyColDirection) &&
                board.fields[row - 1][col + enemyColDirection].color == currentPlayer;
    }

    private int askUserForBoardSize() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What size do you want to play on ? ");
            String size = scanner.nextLine().strip();
            if (validateUserChoicePattern(size)) {
                int boardSize = Integer.parseInt(size);
                if (boardSize >= 10 && boardSize <= 20 && boardSize % 2 == 0) return boardSize;
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
