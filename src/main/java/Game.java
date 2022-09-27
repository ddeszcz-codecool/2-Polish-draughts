import java.util.Scanner;

public class Game {
    Color currentPlayer = Color.WHITE;
    Board board;

    void start() {
        System.out.println("Hello !!");
        System.out.println("What size do you want to play on ? ");
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

        while (true) {
            if (!verifyPlayerMove()) {
                continue;
            }

            if (checkForWinner()) {
                    return false;
            }

            if (currentPlayer == Color.WHITE) {
                currentPlayer = Color.BLACK;
            } else
                currentPlayer = Color.WHITE;

            break;
        }
        return true;
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
        return false;
    }

    private int askUserForBoardSize() {
        Scanner scanner = new Scanner(System.in);

        return 10;
    }

    //validacja regexa + czy na polu jest pionek gracza + czy koordynat na boardzie
    private boolean validateUserChoosenPawnCoordinates(String coordinates) {
        int[] pawnPlace = stringToCoordinates(coordinates);
        if(board.getFields()[pawnPlace[0]][pawnPlace[1]] == null){
            System.out.println("blad");
            return false;
        }
        return true;
    }

    //validacja regexa + czy koordynat na boardzie
    private boolean validateUserChoosenMoveCoordinates(String coordinates) {
        return true;
    }

    private int[] stringToCoordinates(String coordinates) {
        char charRow = coordinates.charAt(0);
        int row = charRow - 97;
        int col = Integer.parseInt(coordinates.substring(1)) - 1;

        return new int[]{col, row};
    }
}
