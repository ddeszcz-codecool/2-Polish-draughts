
public class Game {
    //Pawn pawn = new Pawn();

    void start(){

    }
    public void playRound(){// Który gracz jest następny,oraz czy ktoś wygrał.

    }
    public void verifyPawnCoordinates (Pawn pawn){ //Sprawdza czy jest w zakresie i czy nie jest już zajęty i wywołuje metode tryToMakeMove
    pawn.tryToMakeMove();
    }
    public boolean checkForWinner(){//Sprawdza czy po rundzie ktoś wygrywa, oraz czy jest remis
        //Wygrana kiedy wszystkie pionki przeciwnika znikną
        //Zrobić log ostatnich zagrać
        return false;
    }
}
