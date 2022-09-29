
public class Pawn {

    Color color = null;
    Coordinates position = new Coordinates(0, 0);
    boolean isCrowned = false;

    Pawn(int x, int y, Color color) {
        position = new Coordinates(x, y);
        this.color = color;
    }

    Pawn() {
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    Color getColor() {
        return color;
    }

    boolean isItMove(int[] newCoordinates, Board board) {
        return pawnMove(newCoordinates, board);
    }

    private boolean pawnMove(int[] newCoordinates, Board board) {
        if (this.color == Color.WHITE)
            return whitePawnMove(newCoordinates, board);
        else
            return blackPawnMove(newCoordinates, board);
    }

    private boolean whitePawnMove(int[] newCoordinates, Board board) {
        return board.getFields()[newCoordinates[0]][newCoordinates[1]] == null &&
                (this.position.getX() - 1 == newCoordinates[0] && this.position.getY() + 1 == newCoordinates[1]) ||
                (this.position.getX() - 1 == newCoordinates[0] && this.position.getY() - 1 == newCoordinates[1]);
    }

    private boolean blackPawnMove(int[] newCoordinates, Board board) {
        return board.getFields()[newCoordinates[0]][newCoordinates[1]] == null &&
                (this.position.getX() + 1 == newCoordinates[0] && this.position.getY() - 1 == newCoordinates[1]) ||
                (this.position.getX() + 1 == newCoordinates[0] && this.position.getY() + 1 == newCoordinates[1]);
    }

    boolean isItCapture(int[] newCoordinates, Board board) {
        return pawnCapture(newCoordinates, board);
    }

    private boolean pawnCapture(int[] newCoordinates, Board board) {
        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] == null) {
            int possibleEnemyPawnX = (this.position.getX() + newCoordinates[0]) / 2;
            int possibleEnemyPawnY = (this.position.getY() + newCoordinates[1]) / 2;

            if (this.position.getX() != 0 && this.position.getY() != board.fields.length - 1 &&
                    board.getFields()[possibleEnemyPawnX][possibleEnemyPawnY] != null &&
                    board.getFields()[possibleEnemyPawnX][possibleEnemyPawnY].getColor() != this.getColor()) {
                return true;
            }
        }
        return false;
    }


    public boolean tryToMakeMove(int[] newCoordinates, Board board) {
        if (isItMove(newCoordinates, board)) {
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            return true;
        }
        if (isItCapture(newCoordinates, board)) {
            board.removePawn(board.getFields()[(this.position.getX() + newCoordinates[0]) / 2][(this.position.getY() + newCoordinates[1]) / 2]);
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            return true;
        }
        return false;
    }
}