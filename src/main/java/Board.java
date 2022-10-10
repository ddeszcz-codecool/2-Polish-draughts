class Board {
    final private int initPawnRows = 4;
    final private int WHITE_STARTING_ROW;
    final private int BLACK_STARTING_ROW = 0;
    private Pawn[][] fields;
    private DisplayBoard dis;

    Board(int n) {
        this.fields = new Pawn[n][n];
        WHITE_STARTING_ROW = fields.length - 1;
        fields[4][3] = new Pawn(4, 3, Color.WHITE);
        fields[5][4] = new Pawn(5, 4, Color.BLACK);
        fields[6][5] = new Pawn(6, 5, Color.BLACK);

//        fields[8][1] = new Pawn(8, 1, Color.BLACK);
//        fields[9][2] = new Pawn(9, 2, Color.BLACK);
//        fields[9][0] = new Pawn(9, 0, Color.BLACK);
        fields[1][2] = new Pawn(1, 2, Color.WHITE);
//         fields[0][1] = new Pawn(0, 1, Color.WHITE);
         fields[8][3] = new Pawn(8, 3, Color.BLACK);
         fields[1][4] = new Pawn(1, 4, Color.BLACK);
// //       fields[1][2] = new Pawn(1, 2, Color.BLACK);
 //       setUpPawnsOnBoard();
    }

    private void setUpPawnsOnBoard() {
        setUpWhitePawns();
        setUpBlackPawns();
    }

    private void setUpWhitePawns() {
        int startingColumn;
        for (int i = WHITE_STARTING_ROW; i >= fields.length - initPawnRows; i--) {
            if (i % 2 == 0)
                startingColumn = 1;
            else
                startingColumn = 0;
            for (int j = startingColumn; j < fields[0].length; j += 2) {
                fields[i][j] = new Pawn(i, j, Color.WHITE);
            }
        }
    }

    private void setUpBlackPawns() {
        int startingColumn;
        for (int i = BLACK_STARTING_ROW; i < initPawnRows; i++) {
            if (i % 2 == 0)
                startingColumn = 1;
            else
                startingColumn = 0;

            for (int j = startingColumn; j < fields[0].length; j += 2) {
                fields[i][j] = new Pawn(i, j, Color.BLACK);
            }
        }
    }

    public String toString() {
        dis = new DisplayBoard(fields);
        dis.ShowMeBoard();
        return "";
    }

    public void removePawn(Pawn pawn) {
        fields[pawn.position.getX()][pawn.position.getY()] = null;
    }

    public void movePawn(Pawn pawn, int newX, int newY) {
        int currentX = pawn.position.getX();
        int currentY = pawn.position.getY();

        fields[newX][newY] = pawn;
        pawn.position.setNewCoordinates(newX, newY);
        fields[currentX][currentY] = null;
    }

    public Pawn[][] getFields() {
        return fields;
    }
}
