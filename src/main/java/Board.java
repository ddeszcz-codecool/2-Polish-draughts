class Board {
    final int initPawnRows = 4;
    Pawn[][] fields;
    DisplayBoard dis;

    Board(int n) {
        this.fields = new Pawn[n][n];
        //setUpPawnsOnBoard();
        fields[1][2] = new Pawn(1,2,Color.WHITE);
        fields[0][1] = new Pawn(0,1,Color.WHITE);
        fields[4][5] = new Pawn(4,5,Color.BLACK);
        fields[6][5] = new Pawn(6,5,Color.BLACK);
        fields[8][5] = new Pawn(8,5,Color.BLACK);
    }

    private void setUpPawnsOnBoard() {
        int startingColumn;

        //for white pawns
        for (int i = fields.length - 1; i >= fields.length - initPawnRows; i--) {
            if (i % 2 == 0)
                startingColumn = 1;
            else
                startingColumn = 0;
            for (int j = startingColumn; j < fields[0].length; j += 2) {
                fields[i][j] = new Pawn(i, j, Color.WHITE);
            }
        }

        //for black pawns

        for (int i = 0; i < initPawnRows; i++) {
            if (i % 2 == 0)
                startingColumn = 1;
            else
                startingColumn = 0;

            for (int j = startingColumn; j < fields[0].length; j += 2) {
                fields[i][j] = new Pawn(i, j, Color.BLACK);
            }
        }
    }

    public String toString() { //Wyprintowanie szachownicy i pionków
        dis = new DisplayBoard(fields);
        dis.ShowMeBoard();
        return "Hey";
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
