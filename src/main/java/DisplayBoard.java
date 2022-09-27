public class DisplayBoard {
    Pawn [][] paraBoard;
    int n;
    DisplayBoard(Pawn[][] board){
        paraBoard = board;
        n  = paraBoard.length;
    }
     String whitepiece = "(░)";
     String blackpiece = "( )";
     String whitequeen = "[░]";
     String blackqueen = "[▒]";
     String blacksquare = "   ";
     String whitesquare = "▓▓▓";
// ===================================
// blacksquares are playable
// whitesquares are not
     String letters = "ABCDEFGHIJKLMNOPQRST";



    //static int [][] paraBoard = new int [n][n];


    public void ShowMeBoard() {
        int xct, yct, fieldContent;
        System.out.print("      ");
        for (int iCol = 0; iCol < n; iCol++) {
            System.out.print(letters.charAt(iCol)+"  ");
        }
        System.out.print("\n");

        for (int iRow = 0; iRow < n; iRow++) {
            String formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(formatted);
            for (int iCol = 1; iCol < n; iCol += 2) {

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
                System.out.print(determineFieldContent(iRow,iCol)); // this is always empty
            }
            System.out.print("\n");
            iRow++;
            formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(formatted);
            for (int iCol = 0; iCol < n; iCol += 2) {
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
                System.out.print(determineFieldContent(iRow,iCol)); // this is always empty
                System.out.print(whitesquare); // th

            }
            System.out.print("\n");
        }


    }
    private String determineFieldContent(int row, int col){
        if(paraBoard[row][col] == null){
            return blacksquare;
        }

        if(paraBoard[row][col].color == Color.BLACK){
            return blackpiece;
        }
        else
            return whitepiece;
    }


//
//    public static void main(String[] args) {
////        DisplayBoard displayboard = new DisplayBoard();
//        ShowMeBoard(n);
//    }


}