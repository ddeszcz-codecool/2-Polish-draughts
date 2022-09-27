
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

    public boolean isCrowned() { //Sprawdza czy pionek ma być koronowany?
        return isCrowned;
    }

    Color getColor() { //Pozyskanie koloru?
        return color;
    }

    boolean moveIsCorrect(int[] newCoordinates, Board board) { //Metoda sprawdzam czy miejsce, na które się ruszyłeś jest puste?
        if (pawnMove(newCoordinates, board)) {
            return true;
        }
        if (pawnCapture(newCoordinates, board)) {
            return true;
        }
        return false;
    }

    private boolean pawnCapture(int[] newCoordinates, Board board) {
        if(this.color == Color.WHITE){
            return whitePawnCapturesBlack(newCoordinates, board);
        }
        else
            return blackPawnCapturesWhite(newCoordinates, board);
    }

    private boolean whitePawnCapturesBlack(int[] newCoordinates, Board board) {
        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] == null) {
            if (newCoordinates[1] > this.position.getY()) {
                return board.getFields()[newCoordinates[0] - 1][newCoordinates[1] + 1].color == Color.BLACK;
            }
            if(newCoordinates[1] < this.position.getY()){
                return board.getFields()[newCoordinates[0] - 1][newCoordinates[1] - 1].color == Color.BLACK;
            }
        }
        return false;
    }

    private boolean blackPawnCapturesWhite(int[] newCoordinates, Board board) {
        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] == null) {
            if (newCoordinates[1] > this.position.getY()) {
                return board.getFields()[newCoordinates[0] + 1][newCoordinates[1] + 1].color == Color.WHITE;
            }
            if(newCoordinates[1] < this.position.getY()){
                return board.getFields()[newCoordinates[0] + 1][newCoordinates[1] - 1].color == Color.WHITE;
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


    //sprawdza czy mozna sie ruszyc w dane coordynaty
    //wykonuje ruch
    public boolean tryToMakeMove(int[] newCoordinates, Board board) {
        if(moveIsCorrect(newCoordinates, board)) {
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            if(this.position.getX() + 2 == newCoordinates[0] || this.position.getX() - 2 == newCoordinates[0]){

            }
            return true;
        }
        return false;
    }
}