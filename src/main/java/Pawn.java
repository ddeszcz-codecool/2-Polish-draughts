
public class Pawn {

    Color color = null;
    private int pawnToDeleteDirection;
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

    Color getColor() { //Pozyskanie koloru?
        return color;
    }

    boolean isItMove(int[] newCoordinates, Board board) {
        return pawnMove(newCoordinates, board);
    }

    boolean isItCapture(int[] newCoordinates, Board board) {
        return pawnCapture(newCoordinates, board);
    }

    private boolean pawnCapture(int[] newCoordinates, Board board) {
        if (this.getColor() == Color.WHITE) {
            return whitePawnCapturesBlack(newCoordinates, board);
        } else
            return blackPawnCapturesWhite(newCoordinates, board);
    }

    private boolean whitePawnCapturesBlack(int[] newCoordinates, Board board) {
        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] == null) {
            if (newCoordinates[1] > this.position.getY()) {
                if (board.getFields()[this.position.getX() - 1][this.position.getY() + 1] != null &&
                        board.getFields()[this.position.getX() - 1][this.position.getY() + 1].getColor() == Color.BLACK) {
                    pawnToDeleteDirection = 1;
                    return true;
                }
            }
            if (newCoordinates[1] < this.position.getY()) {
                if (board.getFields()[this.position.getX() - 1][this.position.getY() - 1] != null &&
                        board.getFields()[this.position.getX() - 1][this.position.getY() - 1].getColor() == Color.BLACK) {
                    pawnToDeleteDirection = -1;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean blackPawnCapturesWhite(int[] newCoordinates, Board board) {
        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] == null) {
            if (newCoordinates[1] > this.position.getY()) {
                if (board.getFields()[this.position.getX() + 1][this.position.getY() + 1] != null &&
                        board.getFields()[this.position.getX() + 1][this.position.getY() + 1].getColor() == Color.WHITE) {
                    pawnToDeleteDirection = 1;
                    return true;
                }
            }
            if (newCoordinates[1] < this.position.getY()) {
                if (board.getFields()[this.position.getX() + 1][this.position.getY() - 1] != null &&
                        board.getFields()[this.position.getX() + 1][this.position.getY() - 1].getColor() == Color.WHITE) {
                    pawnToDeleteDirection = -1;
                    return true;
                }
            }
        }
        return false;
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
    public boolean tryToMakeMove(int[] newCoordinates, Board board) {
        if (isItMove(newCoordinates, board)) {
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            return true;
        }
        if (isItCapture(newCoordinates, board)) {
            board.removePawn(board.getFields()[(this.position.getX() + newCoordinates[0])/2][(this.position.getY() + newCoordinates[1])/2]);
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            return true;
        }
        return false;
    }
}