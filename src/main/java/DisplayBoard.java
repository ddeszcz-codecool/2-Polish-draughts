public class DisplayBoard {
    Pawn[][] gameBoard;
    int boardLength;

    DisplayBoard(Pawn[][] board) {
        gameBoard = board;
        boardLength = gameBoard.length;
    }

    private static String DARK_BACKGROUND = "\033[48;2;127;66;40m";
    private static String DARK_FOREGROUND = "\033[38;2;127;66;40m";
    private static String BLACK_PAWN_FOREGROUND = "\033[38;2;0;0;0m";
    private static String EMPTY_BACKGROUND = "\033[48;2;234;240;174m";
    private static String EMPTY_FOREGROUND = "\033[38;2;234;240;174m";
    private static String WHITE_PAWN_FOREGROUND = "\033[38;2;255;255;255m";
    private static String SIDE_FOREGROUND = "\033[38;2;213;194;62m";
    ;
    private static String COLOR_RESET = "\033[0m";
    private static String BOARD_COLOUR_BACKGROUND = "\033[48;2;130;70;40m";
    private static String BOARD_COLOUR_FOREGROUND = "\033[38;2;130;70;40m";


    private static String WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
    private static String BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
    private static String WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
    private static String BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
    private static String BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;
    private static String WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;
    private final String letters = "ABCDEFGHIJKLMNOPQRST";
    private static String EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";


    public void ShowMeBoard() {
//        chooseTemplate(); //TEST
        System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + "     \u2008");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(SIDE_FOREGROUND + letters.charAt(iCol) + EMPTY_SPACE);
        }
        System.out.println("  \u2002" + COLOR_RESET);

        for (int iRow = 0; iRow < boardLength; iRow++) {
            String formatted = String.format("%2s", (iRow + 1)) + " ";
            System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + " " + SIDE_FOREGROUND + formatted);
            for (int iCol = 1; iCol < boardLength; iCol += 2) {
                System.out.print(WHITE_SQUARE);
                System.out.print(determineFieldContent(iRow, iCol));
            }
            System.out.println(BOARD_COLOUR_BACKGROUND + " " + SIDE_FOREGROUND + formatted + "\u200A" + COLOR_RESET);
            iRow++;
            formatted = String.format("%2s", (iRow + 1)) + " ";
            System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + " " + SIDE_FOREGROUND + formatted);
            for (int iCol = 0; iCol < boardLength; iCol += 2) {
                System.out.print(determineFieldContent(iRow, iCol));
                System.out.print(WHITE_SQUARE);

            }
            System.out.println(BOARD_COLOUR_BACKGROUND + " " + SIDE_FOREGROUND + formatted + "\u200A" + COLOR_RESET);
        }
        System.out.print("\u2004" + BOARD_COLOUR_BACKGROUND + "     \u2008");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(SIDE_FOREGROUND + letters.charAt(iCol) + EMPTY_SPACE);
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

    public static void chooseTemplate(int theme) {
        switch (theme) {
            case 1: {
                SIDE_FOREGROUND = "\033[38;2;232;9;57m";

                BOARD_COLOUR_FOREGROUND = "\033[38;2;10;10;10m";
                BOARD_COLOUR_BACKGROUND = "\033[48;2;10;10;10m";
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";

                DARK_FOREGROUND = "\033[38;2;10;10;10m";
                DARK_BACKGROUND = "\033[48;2;10;10;10m";
                BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;

                EMPTY_FOREGROUND = "\033[38;2;102;0;0m";
                EMPTY_BACKGROUND = "\033[48;2;102;0;0m";
                WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;

                BLACK_PAWN_FOREGROUND = "\033[38;2;232;9;57m";

                WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                break;
            }   //Red leather Style
            case 2: {
                SIDE_FOREGROUND = "\033[38;2;255;255;255m";

                BOARD_COLOUR_FOREGROUND = "\033[38;2;49;52;58m";
                BOARD_COLOUR_BACKGROUND = "\033[48;2;49;52;58m";
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";

                DARK_FOREGROUND = "\033[38;2;5;5;5m";
                DARK_BACKGROUND = "\033[48;2;5;5;5m";
                BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;

                EMPTY_FOREGROUND = "\033[38;2;250;250;250m";
                EMPTY_BACKGROUND = "\033[48;2;250;250;250m";
                WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;

                BLACK_PAWN_FOREGROUND = "\033[38;2;217;17;17m";
                WHITE_PAWN_FOREGROUND = "\033[38;2;60;128;216m";

                WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                break;
            }   //Black and White
            case 3: {
                SIDE_FOREGROUND = "\033[38;2;232;235;239m";

                BOARD_COLOUR_FOREGROUND = "\033[38;2;0;74;158m";
                BOARD_COLOUR_BACKGROUND = "\033[48;2;0;74;158m";
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";

                DARK_FOREGROUND = "\033[38;2;125;135;150m";
                DARK_BACKGROUND = "\033[48;2;125;135;150m";
                BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;

                EMPTY_FOREGROUND = "\033[38;2;232;235;239m";
                EMPTY_BACKGROUND = "\033[48;2;232;235;239m";
                WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;

                WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                break;
            }   //Winter edition
            case 4: {
                SIDE_FOREGROUND = "\033[38;2;67;139;121m";

                BOARD_COLOUR_FOREGROUND = "\033[38;2;49;52;58m";
                BOARD_COLOUR_BACKGROUND = "\033[48;2;49;52;58m";
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";

                DARK_FOREGROUND = "\033[38;2;67;139;121m";
                DARK_BACKGROUND = "\033[48;2;67;139;121m";
                BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;

                EMPTY_FOREGROUND = "\033[38;2;186;186;186m";
                EMPTY_BACKGROUND = "\033[48;2;186;186;186m";
                WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;

                BLACK_PAWN_FOREGROUND = "\033[38;2;15;15;15m";
                WHITE_PAWN_FOREGROUND = "\033[38;2;220;220;220m";

                WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                break;
            }   //Green
            case 5: {

                BOARD_COLOUR_BACKGROUND = "\033[48;2;0;0;0m";
                BOARD_COLOUR_FOREGROUND = "\033[38;2;0;0;0m";
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";

                break;
            }   //Default board with black frame
            case 6: {
                DARK_BACKGROUND = "\033[48;2;127;66;40m";
                DARK_FOREGROUND = "\033[38;2;127;66;40m";
                BLACK_PAWN_FOREGROUND = "\033[38;2;0;0;0m";
                EMPTY_BACKGROUND = "\033[48;2;234;240;174m";
                EMPTY_FOREGROUND = "\033[38;2;234;240;174m";
                WHITE_PAWN_FOREGROUND = "\033[38;2;255;255;255m";
                SIDE_FOREGROUND = "\033[38;2;213;194;62m";
                BOARD_COLOUR_BACKGROUND = "\033[48;2;130;70;40m";
                BOARD_COLOUR_FOREGROUND = "\033[38;2;130;70;40m";

                WHITE_PIECE = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                BLACK_PIECE = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u26C2 " + COLOR_RESET;
                WHITE_QUEEN = DARK_BACKGROUND + WHITE_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_QUEEN = DARK_BACKGROUND + BLACK_PAWN_FOREGROUND + " \u265B " + COLOR_RESET;
                BLACK_SQUARE = DARK_BACKGROUND + DARK_FOREGROUND + " \u26C0 " + COLOR_RESET;
                WHITE_SQUARE = EMPTY_BACKGROUND + EMPTY_FOREGROUND + " \u26C0 " + COLOR_RESET;
                EMPTY_SPACE = BOARD_COLOUR_FOREGROUND + " \u26C0";


            }   //Default
        }

    }
}