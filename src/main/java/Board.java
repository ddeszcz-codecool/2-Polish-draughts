class Board {


    final int innitPawnRows = 4;
    Pawn[][] fields;
    DisplayBoard dis;

    Board(int n) {
        this.fields = new Pawn[n][n];
        setUpPawnsOnBoard();
    }
    private void setUpPawnsOnBoard() {
        int startingColumn;

        //for white pawns
        for (int i = fields.length - 1; i >= fields.length - innitPawnRows; i--) {
            if (fields.length % 2 == 0)
                startingColumn = 0;
            else
                startingColumn = 1;
            for (int j = startingColumn; j < fields[0].length; j += 2) {
                fields[i][j] = new Pawn(i, j, Color.WHITE);
            }
        }

        //for black pawns

        for (int i = 0; i < innitPawnRows; i++) {
            if (fields.length % 2 == 0)
                startingColumn =0;
            else
                startingColumn = 1;

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
