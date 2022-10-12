
public class Pawn {
    private static class MoveParameters {
        int rowStartingPoint;
        int rowEndPoint;
        int colStartingPoint;
        int colEndPoint;
        int colDirection;

        public MoveParameters(int rowStartingPoint, int rowEndPoint, int colStartingPoint, int colEndPoint, int colDirection) {
            this.rowStartingPoint = rowStartingPoint;
            this.rowEndPoint = rowEndPoint;
            this.colStartingPoint = colStartingPoint;
            this.colEndPoint = colEndPoint;
            this.colDirection = colDirection;
        }
    }

    private final Color color;
    Coordinates position;
    public boolean isCrowned = false;
    private final int[] enemyPawnCoordinatesForCapture = new int[2];

    Pawn(int x, int y, Color color) {
        position = new Coordinates(x, y);
        this.color = color;
    }

    public MoveParameters calculateMoveDirection(Pawn currentPawn, int[] newCoordinates) {
        Coordinates position = currentPawn.position;
        int rowStartingPoint = Math.min(position.getX(), newCoordinates[0]) + 1;
        int rowEndPoint = Math.max(position.getX(), newCoordinates[0]);

        int colStartingPoint = rowStartingPoint == newCoordinates[0] + 1 ? newCoordinates[1] : position.getY();
        int colEndPoint = rowEndPoint == newCoordinates[0] ? newCoordinates[1] : position.getY();

        int colDirection = colStartingPoint < colEndPoint ? 1 : -1;

        return new MoveParameters(rowStartingPoint, rowEndPoint, colStartingPoint, colEndPoint, colDirection);
    }

    private boolean isItMove(int[] newCoordinates, Board board) {
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

    private boolean isItCapture(int[] newCoordinates, Board board) {
        if (!isCaptureAllowed(newCoordinates))
            return false;

        if (board.getFields()[newCoordinates[0]][newCoordinates[1]] != null) {
            return false;
        }
        Coordinates possibleEnemyPawnCoords = calculateMiddlePoint(position, newCoordinates);

        return board.getFields()[possibleEnemyPawnCoords.getX()][possibleEnemyPawnCoords.getY()] != null &&
                board.getFields()[possibleEnemyPawnCoords.getX()][possibleEnemyPawnCoords.getY()].getColor() != this.getColor();
    }

    private boolean isCaptureAllowed(int[] newCoordinates) { //toRemove
        return (Math.abs(position.getX() - newCoordinates[0]) == 2) &&
                (Math.abs(position.getY() - newCoordinates[1]) == 2);
    }

    private boolean isItQueenMove(MoveParameters moveParameters, Board board) {
        int currentCol = moveParameters.colStartingPoint;
        int currentRow = moveParameters.rowStartingPoint;
        for (; currentRow < moveParameters.rowEndPoint; currentRow++) {
            currentCol += moveParameters.colDirection;
            if (board.getFields()[currentRow][currentCol] != null)
                return false;
        }
        return true;
    }

    private boolean isItQueenCapture(MoveParameters moveParameters, Board board) {
        int currentCol = moveParameters.colStartingPoint;
        int currentRow = moveParameters.rowStartingPoint;
        int enemyPawnsInLine = 0;

        for (; currentRow < moveParameters.rowEndPoint; currentRow++) {
            currentCol += moveParameters.colDirection;

            if (board.getFields()[currentRow][currentCol] == null) {
                continue;
            }

            if (board.getFields()[currentRow][currentCol].color == this.color) {
                return false;
            }

            if (board.getFields()[currentRow][currentCol].color != this.color) {
                enemyPawnsInLine++;
                if (enemyPawnsInLine > 1) {
                    return false;
                }
                enemyPawnCoordinatesForCapture[0] = currentRow;
                enemyPawnCoordinatesForCapture[1] = currentCol;
            }
        }
        return enemyPawnsInLine != 0;

    }

    public PawnMoveStatus tryToMakeMove(int[] newCoordinates, Board board) {
        if (isCrowned) {
            return tryToMoveQueen(newCoordinates, board);
        } else {
            return tryToMovePawn(newCoordinates, board);
        }
    }

    public PawnMoveStatus tryToCapture(int[] newCoordinates, Board board) {
        if (isCrowned) {
            MoveParameters moveParameters = calculateMoveDirection(this, newCoordinates);
            return queenCapture(moveParameters, newCoordinates, board);
        }else{
            return pawnCapture(newCoordinates,board);
        }
    }

    private PawnMoveStatus tryToMovePawn(int[] newCoordinates, Board board) {
        if (isItMove(newCoordinates, board)) {
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            verifyIfCanBeCrowned(board);
            return PawnMoveStatus.SUCCESSFUL_NO_MORE_CAPTURE;
        }
        return pawnCapture(newCoordinates, board);

    }

    private PawnMoveStatus pawnCapture(int[] newCoordinates, Board board) {
        if (isItCapture(newCoordinates, board)) {
            Coordinates pawnToRemoveCoords = calculateMiddlePoint(this.position, newCoordinates);
            board.removePawn(board.getFields()[pawnToRemoveCoords.getX()][pawnToRemoveCoords.getY()]);
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            verifyIfCanBeCrowned(board);
            if (canMakeAnotherCapture(board))
                return PawnMoveStatus.SUCCESSFUL_CAN_CAPTURE_AGAIN;

            return PawnMoveStatus.SUCCESSFUL_NO_MORE_CAPTURE;
        }
        return PawnMoveStatus.UNSUCCESSFUL;
    }


    private PawnMoveStatus tryToMoveQueen(int[] newCoordinates, Board board) {
        MoveParameters moveParameters = calculateMoveDirection(this, newCoordinates);

        if (isItQueenMove(moveParameters, board)) {
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            return PawnMoveStatus.SUCCESSFUL_NO_MORE_CAPTURE;
        }
        return queenCapture(moveParameters, newCoordinates, board);

    }

    private PawnMoveStatus queenCapture(MoveParameters moveParameters, int[] newCoordinates, Board board) {
        if (isItQueenCapture(moveParameters, board)) {
            board.removePawn(board.getFields()[enemyPawnCoordinatesForCapture[0]][enemyPawnCoordinatesForCapture[1]]);
            board.movePawn(this, newCoordinates[0], newCoordinates[1]);
            if (canMakeAnotherCapture(board))
                return PawnMoveStatus.SUCCESSFUL_CAN_CAPTURE_AGAIN;

            return PawnMoveStatus.SUCCESSFUL_NO_MORE_CAPTURE;
        }
        return PawnMoveStatus.UNSUCCESSFUL;
    }

    private Coordinates calculateMiddlePoint(Coordinates firstPoint, int[] secondPoint) {
        return new Coordinates((firstPoint.getX() + secondPoint[0]) / 2,
                (firstPoint.getY() + secondPoint[1]) / 2);
    }

    private void verifyIfCanBeCrowned(Board board) {
        if (color == Color.WHITE) {
            if (position.getX() == 0) {
                isCrowned = true;
            }
        } else if (position.getX() == board.getFields().length - 1)
            isCrowned = true;
    }

    private boolean canMakeAnotherCapture(Board board) {
        int[] rowDirections = {1, -1, 1, -1};
        int[] colDirections = {1, -1, -1, 1};

        if (isCrowned) {
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

        if (isIndexOutOfBounds(position.getX() + rowJump, board) &&
                isIndexOutOfBounds(position.getY() + colJump, board))
            return false;

        if (!isFieldEmpty(position.getX() + currentRowDirection, position.getY() + currentColDirection, board) &&
                isEnemyPawn(position.getX() + currentRowDirection, position.getY() + currentColDirection, board)) {
            return isFieldEmpty(position.getX() + rowJump, position.getY() + colJump, board);
        }
        return false;
    }

    private boolean isCapturePossibleForQueenInSpecificDirection(int currentRowDirection, int currentColDirection, Board board) {
        Coordinates coordsToCheck = new Coordinates(position.getX() + currentRowDirection, position.getY() + currentColDirection);

        while (!isIndexOutOfBounds(coordsToCheck.getX(), board) && !isIndexOutOfBounds(coordsToCheck.getY(), board)) {
            if (isFieldEmpty(coordsToCheck.getX(), coordsToCheck.getY(), board)) {
                coordsToCheck.moveCoordinates(currentRowDirection, currentColDirection);
                continue;
            }
            if (!isEnemyPawn(coordsToCheck.getX(), coordsToCheck.getY(), board))
                return false;

            coordsToCheck.moveCoordinates(currentRowDirection, currentColDirection);

            return !isIndexOutOfBounds(coordsToCheck.getX(), board) && !isIndexOutOfBounds(coordsToCheck.getY(), board) &&
                    isFieldEmpty(coordsToCheck.getX(), coordsToCheck.getY(), board);
        }
        return false;
    }

    private boolean isFieldEmpty(int row, int col, Board board) {
        return board.getFields()[row][col] == null;
    }

    private boolean isIndexOutOfBounds(int index, Board board) {
        return (index < 0 || index >= board.getFields()[0].length);
    }

    private boolean isEnemyPawn(int row, int col, Board board) {
        return board.getFields()[row][col].color != this.color;
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    public Color getColor() {
        return color;
    }
}