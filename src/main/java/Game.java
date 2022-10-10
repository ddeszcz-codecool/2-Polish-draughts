import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    private Color currentPlayer = Color.WHITE;
    private Board board;

    void start() {
        System.out.println("Hello !!");
        board = Board.getBoard(askUserForBoardSize());
        System.out.println(board);
        System.out.println("Current player for - " + currentPlayer.name());
        while (playRound()) {
            System.out.println(board);
            System.out.println("Current player for - " + currentPlayer.name());
        }

        System.out.println(board);
        System.out.println("The Winner is " + currentPlayer + " player !!");
    }

    private boolean playRound() {

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

    private boolean verifyPlayerMove() {
        int[] pawnCoordinates = choosePawnToMove();
        int[] moveCoordinates = chooseCoordinatesToMoveTo();

        if (!board.getFields()[pawnCoordinates[0]][pawnCoordinates[1]].tryToMakeMove(moveCoordinates, board)) {
            System.out.println("Move is not valid. Please choose again");
            return false;
        }
        return true;
    }

    private int[] choosePawnToMove() {
        Scanner scanner = new Scanner(System.in);

        boolean pawnNotChosen = true;
        int[] pawnCoordinates = new int[2];

        while (pawnNotChosen) {
            System.out.println();
            System.out.println("Which pawn do you want to move?");
            String pawnPlaceOnBoard = scanner.nextLine().strip().toLowerCase();

            if (!validateCoordinatesAreOnlyLetterAndNumber(pawnPlaceOnBoard)) {
                System.out.println("Your coordinates are not correct, please use a following format - letter + number - e.g. A2, B4");
                continue;
            }
            int[] chosenCoordinates = stringToCoordinates(pawnPlaceOnBoard);

            if (!validateCoordinatesAreInRange(chosenCoordinates)) {
                int boardWidth = board.getFields().length;
                char boardHeight = (char) (board.getFields().length + 64);
                System.out.println("Your coordinates are out of range please use coordinates from 1 to " + boardWidth + " and from A to " + boardHeight);
                continue;
            }
            if (!validateUserChosenPawnNotNullAndNotDifferentColor(chosenCoordinates)) {
                continue;
            }
            pawnCoordinates = chosenCoordinates;
            pawnNotChosen = false;
        }
        return pawnCoordinates;
    }
    private int[] chooseCoordinatesToMoveTo() {
        Scanner scanner = new Scanner(System.in);
        boolean coordinatesNotChosen = true;
        int[] chosenSpotCoordinates = new int[2];

        while(coordinatesNotChosen) {

            System.out.println("Where you want to move it ?");
            String placeToMove = scanner.nextLine().strip().toLowerCase();
            if (!validateCoordinatesAreOnlyLetterAndNumber(placeToMove)) {
                System.out.println("Your coordinates are not correct, please use format letter plus number e.g. A2, B4");
                continue;
            }
            int[] moveCoordinates = stringToCoordinates(placeToMove);

            if (!validateCoordinatesAreInRange(moveCoordinates)) {
                int boardWidth = board.getFields().length;
                char boardHeight = (char) (board.getFields().length + 64);
                System.out.println("Your coordinates are out of range please use coordinates from 1 to " + boardWidth + " and from A to " + boardHeight);
                continue;
            }
            if (!validateUserChosenMoveCoordinatesAreNull(moveCoordinates)) {
                continue;
            }
            chosenSpotCoordinates = moveCoordinates;
            coordinatesNotChosen = false;
        }
        return chosenSpotCoordinates;
    }

    private boolean checkForWinner() {
        return checkIfNoEnemyPawnsOnBoard() || checkIfAllEnemyPawnsBlocked();
    }

    private boolean checkIfNoEnemyPawnsOnBoard() {

        for (int i = 0; i < board.getFields().length; i++) {
            for (int j = 0; j < board.getFields()[0].length; j++) {
                if (board.getFields()[i][j] != null && board.getFields()[i][j].getColor() != currentPlayer) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfAllEnemyPawnsBlocked() {
        for (int row = 0; row < board.getFields().length; row++) {
            for (int col = 0; col < board.getFields()[0].length; col++) {
                if (!isFieldEmpty(row, col) && board.getFields()[row][col].getColor() != currentPlayer) {
                    if (!isPawnBlocked(row, col))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isPawnBlocked(int row, int col) {
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
        return board.getFields()[row][col] == null;
    }

    private boolean isIndexOutOfBounds(int index) {
        return (index < 0 || index >= board.getFields()[0].length);
    }

    private boolean isCapturePossibleDown(int row, int col, int enemyColDirection) {
        int colJump = enemyColDirection * 2;
        if (isIndexOutOfBounds(col + colJump) || isIndexOutOfBounds(row + 2))
            return false;

        if (!isFieldEmpty(row + 2, col + colJump))
            return false;

        return !isFieldEmpty(row + 1, col + enemyColDirection) &&
                board.getFields()[row + 1][col + enemyColDirection].getColor() == currentPlayer;
    }

    private boolean isCapturePossibleUp(int row, int col, int enemyColDirection) {
        int colJump = enemyColDirection * 2;
        if (isIndexOutOfBounds(col + colJump) || isIndexOutOfBounds(row - 2))
            return false;

        if (!isFieldEmpty(row - 2, col + colJump))
            return false;

        return !isFieldEmpty(row - 1, col + enemyColDirection) &&
                board.getFields()[row - 1][col + enemyColDirection].getColor() == currentPlayer;
    }

    private int askUserForBoardSize() {
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
            } else
                System.out.println("The input format incorrect, try numbers between 10 and 20 (only even numbers)");
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

        if (currentPlayer != board.getFields()[pawnPlace[0]][pawnPlace[1]].getColor()) {
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
        char charCol = coordinates.charAt(0);
        int col = charCol - 97;
        int row = Integer.parseInt(coordinates.substring(1)) - 1;

        return new int[]{row, col};
    }

    private boolean validateCoordinatesAreOnlyLetterAndNumber(String coordinates) {
        Pattern pattern = Pattern.compile("^[a-z][0-9]{1,2}$");
        return pattern.matcher(coordinates).matches();
    }

    private boolean validateCoordinatesAreInRange(int[] coordinates) {
        return coordinates[0] >= 0 && coordinates[0] < board.getFields().length && coordinates[1] < board.getFields().length;
    }
}
