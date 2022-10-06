public class DisplayBoard {
    Pawn [][] gameBoard;
    int boardLength;
    DisplayBoard(Pawn[][] board){
        gameBoard = board;
        boardLength = gameBoard.length;
    }
     String whitepiece = "(░)";
     String blackpiece = "( )";
     String whitequeen = "[░]";
     String blackqueen = "[▒]";
     String blacksquare = "   ";
     String whitesquare = "▓▓▓";
     String letters = "ABCDEFGHIJKLMNOPQRST";

    public void ShowMeBoard() {
        System.out.print("      ");
        for (int iCol = 0; iCol < boardLength; iCol++) {
            System.out.print(letters.charAt(iCol)+"  ");
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
                System.out.print(whitesquare);
                System.out.print(determineFieldContent(iRow,iCol));
            }
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
                System.out.print(determineFieldContent(iRow,iCol));
                System.out.print(whitesquare);

            }
            System.out.print("\n");
        }

    }
    private String determineFieldContent(int row, int col){
        if(gameBoard[row][col] == null){
            return blacksquare;
        }

        if(gameBoard[row][col].getColor() == Color.BLACK){
            return blackpiece;
        }
        else
            return whitepiece;
    }
}