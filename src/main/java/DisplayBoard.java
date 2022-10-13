public class DisplayBoard {
    Pawn[][] gameBoard;
    int boardLength;

    DisplayBoard(Pawn[][] board) {
        gameBoard = board;
        boardLength = gameBoard.length;
    }

    private final String DARK_BACKGROUND = "\033[48;2;127;66;40m";
    private final String BLACK_FOREGROUND = "\033[38;2;0;0;0m";
    private final String WHITE_BACKGROUND = "\033[48;2;234;240;174m";
    private final String WHITE_FOREGROUND = "\033[38;2;255;255;255m";
    private final String GOLD_FOREGROUND = "\033[38;2;213;194;62m";
    private final String COLOR_RESET = "\033[0m";
    private final String BOARD_COLOUR_BACKGROUND = "\033[48;2;130;70;40m";
    private final String BOARD_COLOUR_FOREGROUND = "\033[38;2;130;70;40m";
    private final String WHITE_PIECE = DARK_BACKGROUND + WHITE_FOREGROUND + " \u26C2 " + COLOR_RESET;
    private final String BLACK_PIECE = DARK_BACKGROUND + BLACK_FOREGROUND + " \u26C2 " + COLOR_RESET;
    private final String WHITE_QUEEN = DARK_BACKGROUND + WHITE_FOREGROUND + " \u265B " + COLOR_RESET;
    private final String BLACK_QUEEN = DARK_BACKGROUND + BLACK_FOREGROUND + " \u265B " + COLOR_RESET;
    private final String BLACK_SQUARE = DARK_BACKGROUND + "\033[38;2;127;66;40m \u26C0 " + COLOR_RESET;
    private final String WHITE_SQUARE = WHITE_BACKGROUND + "\033[38;2;234;240;174m \u26C0 " + COLOR_RESET;
    private final String letters = "ABCDEFGHIJKLMNOPQRST";
    private final String EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";


    public void ShowMeBoard() {
        System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + "     \u2008");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(GOLD_FOREGROUND + letters.charAt(iCol) + EMPTY_SPACE);
        }
        System.out.println("  \u2002" + COLOR_RESET);

        for (int iRow = 0; iRow < boardLength; iRow++) {
            String formatted = String.format("%2s",(iRow + 1)) + " ";
            System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + " "  + GOLD_FOREGROUND + formatted);
            for (int iCol = 1; iCol < boardLength; iCol += 2) {
                System.out.print(WHITE_SQUARE);
                System.out.print(determineFieldContent(iRow, iCol));
            }
            System.out.println(BOARD_COLOUR_BACKGROUND + " " + GOLD_FOREGROUND + formatted + "\u200A" + COLOR_RESET);
            iRow++;
            formatted = String.format("%2s",(iRow + 1)) + " ";
            System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + " "  + GOLD_FOREGROUND + formatted);
            for (int iCol = 0; iCol < boardLength; iCol += 2) {
                System.out.print(determineFieldContent(iRow, iCol));
                System.out.print(WHITE_SQUARE);

            }
            System.out.println(BOARD_COLOUR_BACKGROUND + " " + GOLD_FOREGROUND + formatted + "\u200A" + COLOR_RESET);
        }
        System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + "     \u2008");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(GOLD_FOREGROUND + letters.charAt(iCol) + EMPTY_SPACE);
        }
        System.out.println("  \u2002" + COLOR_RESET);

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