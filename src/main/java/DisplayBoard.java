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
    private final String WHITE_PIECE = BLACK_BACKGROUND + WHITE_FOREGROUND + " \u26C0 " + "\033[0m";
    private final String BLACK_PIECE = BLACK_BACKGROUND + BLACK_FOREGROUND + " \u26C2 " + "\033[0m";
    private final String WHITE_QUEEN = BLACK_BACKGROUND + WHITE_FOREGROUND + " \u26C1 " + "\033[0m";
    private final String BLACK_QUEEN = BLACK_BACKGROUND + BLACK_FOREGROUND + " \u26C3 " + "\033[0m";//"[♚]"
    private final String BLACK_SQUARE = BLACK_BACKGROUND + "   \u2008\u200A" + "\033[0m";
    private final String WHITE_SQUARE = WHITE_BACKGROUND + "   \u2008\u200A" + "\033[0m";
    private final String letters = "ABCDEFGHIJKLMNOPQRST";

    public void ShowMeBoard() {
        System.out.print("      ");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print("\u2009" + letters.charAt(iCol) + "  \u2009");
        }
        System.out.print("\n");

        for (int iRow = 0; iRow < boardLength; iRow++) {
            String formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(formatted);
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
            System.out.print(formatted);
            System.out.print("\n");
            iRow++;
            formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(formatted);
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
            System.out.print(formatted);
            System.out.print("\n");
        }
        System.out.print("      ");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print("\u2009" + letters.charAt(iCol) + "  \u2009");
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
}