package main.java;

public class Pawn {

    Color color;
    Coordinates position;
    boolean isCrowned = false;

    public boolean isCrowned(){ //Sprawdza czy pionek ma być koronowany?
        return isCrowned;
    }

    Color getColor(){ //Pozyskanie koloru?
        return color;
    }
    boolean moveIsCorrect(){ //Metoda sprawdzam czy miejsce, na które się ruszyłeś jest puste?
        return false;           //Czy jest w tablicy?
    }                           //Sprawdzić czy masz obowiązkowe bicie???


    public void tryToMakeMove() {// nawiązanie do ruchu z klasy Game
    }
}
