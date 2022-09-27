
public class Pawn {

    Color color = null;
    Coordinates position = new Coordinates(0,0);
    boolean isCrowned = false;

    Pawn(int x, int y, Color color ){
        position = new Coordinates(x, y);
        this.color = color;
    }
    Pawn(){}

    public boolean isCrowned(){ //Sprawdza czy pionek ma być koronowany?
        return isCrowned;
    }

    Color getColor(){ //Pozyskanie koloru?
        return color;
    }
    boolean moveIsCorrect(){ //Metoda sprawdzam czy miejsce, na które się ruszyłeś jest puste?
        return false;           //Czy jest w tablicy?
    }                           //Sprawdzić czy masz obowiązkowe bicie???


    //sprawdza czy mozna sie ruszyc w dane coordynaty
    //wykonuje ruch
    public boolean tryToMakeMove(int[] newCoordinates, Board board) {
        return true;
    }
}
