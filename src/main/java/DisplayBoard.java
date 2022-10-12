public class DisplayBoard {
    Pawn[][] gameBoard;
    int boardLength;

    DisplayBoard(Pawn[][] board) {
        gameBoard = board;
        boardLength = gameBoard.length;
    }

    private final String BLACK_BACKGROUND = "\033[48;2;127;66;40m";
    private final String BLACK_FOREGROUND = "\033[38;2;0;0;0m";
    private final String WHITE_BACKGROUND = "\033[48;2;234;240;174m";
    private final String WHITE_FOREGROUND = "\033[38;2;255;255;255m";
    private final String COLOR_RESET = "\033[0m";
    private final String BOARD_COLOUR = "\033[48;2;66;220;14m";
    private final String WHITE_PIECE = BLACK_BACKGROUND + WHITE_FOREGROUND + " \u26C0 " + COLOR_RESET;
    private final String BLACK_PIECE = BLACK_BACKGROUND + BLACK_FOREGROUND + " \u26C2 " + COLOR_RESET;
    private final String WHITE_QUEEN = BLACK_BACKGROUND + WHITE_FOREGROUND + " \u2655 " + COLOR_RESET;
    private final String BLACK_QUEEN = BLACK_BACKGROUND + BLACK_FOREGROUND + " \u265B " + COLOR_RESET;
    private final String BLACK_SQUARE = BLACK_BACKGROUND + "\033[38;2;127;66;40m \u26C0 " + COLOR_RESET;
    private final String WHITE_SQUARE = WHITE_BACKGROUND + "\033[38;2;234;240;174m \u26C0 " + COLOR_RESET;
    private final String letters = "ABCDEFGHIJKLMNOPQRST";
    private final String EMPTY_SPACE = BOARD_COLOUR + " \u26C0" + COLOR_RESET;
    private String sideNumber;
    //   ONLY COMMENT         String formatted = String.format("%3s", (iRow + 1)) + "  ";


    public void ShowMeBoard() {
        System.out.print(" " + BOARD_COLOUR + "     \u2009");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(WHITE_FOREGROUND + letters.charAt(iCol) + EMPTY_SPACE);
        }
        System.out.print("  \u2009" + COLOR_RESET + "\n");

        for (int iRow = 0; iRow < boardLength; iRow++) {
            sideNumber = (iRow + 1) + "  ";
            System.out.print(" " + BOARD_COLOUR + " " + WHITE_FOREGROUND + sideNumber + COLOR_RESET);
            for (int iCol = 1; iCol < boardLength; iCol += 2) {

// ==========================================================
//      BLACKSQUARE CAN BE EMPTY OR CONTAIN A PAWN OR A QUEEN
//
//                get paraBoard[iRow][iCol]
//                  case
//                        0: blacksquare
//                        1: whitepiece
//                        2: blackpiece
//                        3: whitequeen
//                        4: whitequeen
// ==========================================================
                System.out.print(WHITE_SQUARE);
                System.out.print(determineFieldContent(iRow, iCol));
            }
            System.out.println(sideNumber);
//            System.out.print("\n");
            iRow++;
//            String formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(printSideNumber(sideNumber));
            for (int iCol = 0; iCol < boardLength; iCol += 2) {
// ==========================================================
//      BLACKSQUARE CAN BE EMPTY OR CONTAIN A PAWN OR A QUEEN
//
//                get paraBoard[iRow + 1][iCol + 1]
//                  case
//                        0: blacksquare
//                        1: whitepiece
//                        2: blackpiece
//                        3: whitequeen
//                        4: whitequeen
// ==========================================================
                System.out.print(determineFieldContent(iRow, iCol));
                System.out.print(WHITE_SQUARE);

            }
            System.out.println(printSideNumber(sideNumber));
//            System.out.print("\n");
        }
        System.out.print("      \u2009");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(letters.charAt(iCol) + EMPTY_SPACE);
        }
        System.out.print("\n");

    }

    private String determineFieldContent(int row, int col) {
        if (gameBoard[row][col] == null) {
            return BLACK_SQUARE;
        }

        if (gameBoard[row][col].getColor() == Color.BLACK) {
            if (gameBoard[row][col].isCrowned())
                return BLACK_QUEEN;
            return BLACK_PIECE;
        } else if (gameBoard[row][col].isCrowned())
            return WHITE_QUEEN;
        return WHITE_PIECE;
    }

    private String printSideNumber(String rowNumber) {
         sideNumber = BOARD_COLOUR + "  " + WHITE_FOREGROUND + rowNumber + COLOR_RESET;
        return sideNumber;
    }
}