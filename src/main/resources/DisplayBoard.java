public class DisplayBoard {

// board size
 static int n = 10;

    static String whitepiece = "(░)";
    static String blackpiece = "(▒)";
    static String whitequeen = "[░]";
    static String blackqueen = "[▒]";
    static String whitesquare = "   ";
    static String blacksquare = "▓▓▓";
    static int whitepieceI = 1;
    static int blackpieceI = 2;
    static int whitesquareI = -1;
    static int blacksquareI = 0;
// ===================================
// blacksquares are playable
// whitesquares are not
    static String letters = "ABCDEFGHIJKLMNOPQRST";



    static int [][] paraBoard = new int [n][n];

    public static void ShowMeBoard(int n) {
        int xct, yct, fieldContent;
        System.out.print("      ");
        for (int iCol = 0; iCol < n; iCol++) {
            System.out.print(letters.charAt(iCol)+"  ");
        }
        System.out.print("\n");
        for (int iRow = 0; iRow < n; iRow += 2) {
            String formatted = String.format("%3s", (iRow + 1)) + "  ";
            System.out.print(formatted);
            for (int iCol = 0; iCol < n; iCol += 2) {

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

                System.out.print(blacksquare);
                System.out.print(whitesquare); // this is always empty
            }
            System.out.print("\n");
            formatted = String.format("%3s", (iRow + 2)) + "  ";
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

                System.out.print(whitesquare); // this is always empty
                System.out.print(blacksquare); // th

            }
            System.out.print("\n");
        }


    }



    public static void main(String[] args) {
//        DisplayBoard displayboard = new DisplayBoard();
        ShowMeBoard(n);
    }


}