import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private char[][] board;

    public Board() {
        board = new char[6][7];
    }

    public Board(char[][] board) {
        this.board = new char[6][];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = Arrays.copyOf(board[i], board[i].length);
        }
    }

    public boolean fill(int col, char piece) {
        piece = Character.toLowerCase(piece);
        if (piece != 'o' && piece != 'x') {
            return false;
        } else if (col < 0 || col > 7) {
            return false;
        }
        if (!isPossibleMove(col) || isBoardFull()) {
            return false;
        } else {
            for (int r = board.length - 1; r >= 0; r--) {
                if (board[r][col] == '\0') {
                    board[r][col] = piece;
                    return true;
                }
            }
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public char[][] getBoardDeepCopy() {
        char[][] boardCopy = new char[6][];
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return boardCopy;
    }

    public boolean isBoardFull() {
        for (char[] r : board) {
            for (char p : r) {
                if (p == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPossibleMove(int col) {
        return board[0][col] == '\0';
    }

    public boolean checkWinner(char piece) {
        return checkHorizontal(piece) || checkVertical(piece) || checkUpWardDiag(piece) || checkDownWardDiag(piece);
    }
    public boolean checkWinner() {
        return checkWinner('x') ||checkWinner('o');
    }
    public boolean checkHorizontal(char piece) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == piece
                        && board[row][col + 1] == piece
                        && board[row][col + 2] == piece
                        && board[row][col + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVertical(char piece) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == piece
                        && board[row + 1][col] == piece
                        && board[row + 2][col] == piece
                        && board[row + 3][col] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkUpWardDiag(char piece) {
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == piece
                        && board[row - 1][col + 1] == piece
                        && board[row - 2][col + 2] == piece
                        && board[row - 3][col + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDownWardDiag(char piece) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == piece
                        && board[row + 1][col + 1] == piece
                        && board[row + 2][col + 2] == piece
                        && board[row + 3][col + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String boardToString = "";
        for (char[] r : board) {
            for (char c : r) {
                if (c == '\0') {
                    boardToString += " |";
                } else {
                    boardToString += c + "|";
                }
            }
            boardToString += '\n';
        }
        return boardToString;
    }

    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> possibleMoves = new ArrayList();

        for (int i = 0; i < board[0].length; i++) {
            if (isPossibleMove(i)) {
                possibleMoves.add(i);
            }
        }

        return possibleMoves;
    }
}
