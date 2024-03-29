/*
  CS 442 - Advanced AI: Combinatorial Games
  Michael Salter
*/

public class State {
    private int boardHeight, boardWidth;
    private char[][] board; //[row][column]
    private int moveNumber;
    private boolean isWhitesPly;

    public State(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new char[boardHeight][boardWidth];
        moveNumber = 1;
        isWhitesPly = true;
    }

    public State(int moveNumber, boolean isWhitesPly, char[][] newBoard) {
        this.moveNumber = moveNumber;
        this.isWhitesPly = isWhitesPly;
        this.board = new char[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                this.board[i][j] = newBoard[i][j];
            }
        }
    }

    public void setBoard(final char[][] newBoard) {
        this.board = new char[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                this.board[i][j] = newBoard[i][j];
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setIsWhitesPly(boolean isWhitesPly) {
        this.isWhitesPly = isWhitesPly;
    }

    public boolean getIsWhitesPly() {
        return isWhitesPly;
    }

    public char getPosition(int row, int column) throws ChessError {
        return board[row][column];
    }

    public void setPosition(int row, int column, char position) throws ChessError {
            board[row][column] = position;
    }

    public void move(Move m) {
        char c = board[m.startRow][m.startColumn];
        //Update start position
        board[m.startRow][m.startColumn] = '.';

        //Check for pawn promotion
        if (c == 'p' && m.endRow == 5) {
            c = 'q';
        } else if (c == 'P' && m.endRow == 0) {
            c = 'Q';
        }

        //Update end position
        board[m.endRow][m.endColumn] = c;

        //Update turn and/or move number
        if (!isWhitesPly) {
            moveNumber++;
        }
        isWhitesPly = !isWhitesPly;
    }

    public String toString() {
        String s = moveNumber + " " + (isWhitesPly ? "W" : "B") + "\n";
        for (char[] ca : board) {
            for (char c : ca) {
                s += c;
            }
            s += "\n";
        }
        return s;
    }
}
