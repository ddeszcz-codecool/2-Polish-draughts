public class AiPlayer {
    public Board board;
    public Color playerColor;
    public Coordinates position;
    public Coordinates placeToMove;
    public Coordinates possiblePawnToRemoveFromBoard;

    AiPlayer(Color color, Board board) {
        playerColor = color;
        this.board = board;
    }

    public void playTurn() {
        if (tryToCapture()) {
            placeToMove = null;
            possiblePawnToRemoveFromBoard = null;
            return;
        }
        tryToMove();
        placeToMove = null;
    }

    private boolean tryToCapture() {
        for (int row = 0; row < board.getFields().length; row++)
            for (int col = 0; col < board.getFields()[0].length; col++)
                if (!isFieldEmpty(row, col))
                    if (!isEnemyPawn(row, col, board)) {
                        position = new Coordinates(row,col);
                        if (canCapture()) {
                            doCapture();
                            position = placeToMove;
                            while(canCapture()) {
                                doCapture();
                                position = placeToMove;
                            }
                            return true;
                        }
                    }
        return false;
    }

    private void doCapture() {
        doMove();
        Pawn pawnToRemove = board.getFields()[possiblePawnToRemoveFromBoard.getX()][possiblePawnToRemoveFromBoard.getY()];
        board.removePawn(pawnToRemove);
    }
    private void tryToMove() {
        for (int row = 0; row < board.getFields().length; row++)
            for (int col = 0; col < board.getFields()[0].length; col++)
                if (!isFieldEmpty(row, col))
                    if (!isEnemyPawn(row, col, board)) {
                        position = new Coordinates(row,col);
                        if (canMakeMove()) {
                            doMove();
                            return;
                        }
                    }
    }
    private void doMove(){
        Pawn currentPawn = board.getFields()[position.getX()][position.getY()];
        board.movePawn(currentPawn, placeToMove.getX(), placeToMove.getY());
    }

    private boolean canMakeMove() {
        if(board.getFields()[position.getX()][position.getY()].isCrowned){
            return tryMoveQueen();
        }
        if (playerColor == Color.WHITE)
            return whitePawnMove();
        else
            return blackPawnMove();
    }

    private boolean whitePawnMove() {
        int leftDirection = -1;
        int rightDirection = 1;

        if (whitePawnMoveInSpecificDirection(leftDirection))
            return true;
        return whitePawnMoveInSpecificDirection(rightDirection);
    }

    private boolean whitePawnMoveInSpecificDirection(int direction) {
        if (isIndexOutOfBounds(position.getX() - 1) || isIndexOutOfBounds(position.getY() + direction))
            return false;
        if (isFieldEmpty(position.getX() - 1, position.getY() + direction)) {
            placeToMove = new Coordinates(position.getX() + 1, position.getY() + direction);
            return true;
        }
        return false;
    }

    private boolean blackPawnMove() {
        int leftDirection = -1;
        int rightDirection = 1;

        if (blackPawnMoveInSpecificDirection(leftDirection))
            return true;
        return blackPawnMoveInSpecificDirection(rightDirection);
    }
    private boolean blackPawnMoveInSpecificDirection(int direction) {
        if (isIndexOutOfBounds(position.getX() + 1) || isIndexOutOfBounds(position.getY() + direction))
            return false;
        if (isFieldEmpty(position.getX() + 1, position.getY() + direction)) {
            placeToMove = new Coordinates(position.getX() + 1, position.getY() + direction);
            return true;
        }
        return false;
    }

    private boolean tryMoveQueen(){
        int[] rowDirections = {1, -1, 1, -1};
        int[] colDirections = {1, -1, -1, 1};
        for(int i = 0; i< 4;i++){
            if(tryMoveQueenInSpecificDirection(rowDirections[i],colDirections[i]))
                return true;
        }
        return false;
    }
    private boolean tryMoveQueenInSpecificDirection(int rowDir,int colDir){
        if (isIndexOutOfBounds(position.getX() + rowDir) || isIndexOutOfBounds(position.getY() + colDir))
            return false;
        if (isFieldEmpty(position.getX() + rowDir, position.getY() + colDir)) {
            placeToMove = new Coordinates(position.getX() + rowDir, position.getY() + colDir);
            return true;
        }
        return false;
    }

    private boolean canCapture() {
        int[] rowDirections = {1, -1, 1, -1};
        int[] colDirections = {1, -1, -1, 1};

        if (board.getFields()[position.getX()][position.getY()].isCrowned) {
            for (int i = 0; i < 4; i++) {
                if (isCapturePossibleForQueenInSpecificDirection(rowDirections[i], colDirections[i], board))
                    return true;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (isCapturePossibleForPawnInSpecificDirection(rowDirections[i], colDirections[i], board))
                    return true;
            }
        }
        return false;
    }

    private boolean isCapturePossibleForPawnInSpecificDirection(int currentRowDirection, int currentColDirection, Board board) {
        int rowJump = currentRowDirection * 2;
        int colJump = currentColDirection * 2;

        if (isIndexOutOfBounds(position.getX() + rowJump) ||
                isIndexOutOfBounds(position.getY() + colJump))
            return false;

        if (!isFieldEmpty(position.getX() + currentRowDirection, position.getY() + currentColDirection) &&
                isEnemyPawn(position.getX() + currentRowDirection, position.getY() + currentColDirection, board)) {

            possiblePawnToRemoveFromBoard = new Coordinates(position.getX() + currentRowDirection, position.getY() + currentColDirection);
            if (isFieldEmpty(position.getX() + rowJump, position.getY() + colJump)) {
                placeToMove = new Coordinates(position.getX() + rowJump, position.getY() + colJump);
                return true;
            }
        }
        return false;
    }

    private boolean isCapturePossibleForQueenInSpecificDirection(int currentRowDirection, int currentColDirection, Board board) {
        Coordinates coordsToCheck = new Coordinates(position.getX() + currentRowDirection, position.getY() + currentColDirection);

        while (!isIndexOutOfBounds(coordsToCheck.getX()) && !isIndexOutOfBounds(coordsToCheck.getY())) {
            if (isFieldEmpty(coordsToCheck.getX(), coordsToCheck.getY())) {
                coordsToCheck.moveCoordinates(currentRowDirection, currentColDirection);
                continue;
            }
            if (!isEnemyPawn(coordsToCheck.getX(), coordsToCheck.getY(), board))
                return false;

            possiblePawnToRemoveFromBoard = new Coordinates(coordsToCheck.getX(), coordsToCheck.getY());
            coordsToCheck.moveCoordinates(currentRowDirection, currentColDirection);

            if (!isIndexOutOfBounds(coordsToCheck.getX()) && !isIndexOutOfBounds(coordsToCheck.getY()) &&
                    isFieldEmpty(coordsToCheck.getX(), coordsToCheck.getY())) {
                placeToMove = new Coordinates(coordsToCheck.getX(), coordsToCheck.getY());
                return true;
            }
        }
        return false;
    }

    private boolean isFieldEmpty(int row, int col) {
        return board.getFields()[row][col] == null;
    }

    private boolean isIndexOutOfBounds(int index) {
        return (index < 0 || index >= board.getFields()[0].length);
    }

    private boolean isEnemyPawn(int row, int col, Board board) {
        return board.getFields()[row][col].getColor() != playerColor;
    }

}
