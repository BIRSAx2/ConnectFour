import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private char[][] board;


    public Board() {
        board = new char[6][7];
    }

    /**
     * @param board
     */
    public Board(char[][] board) {
        this.board = new char[6][];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = Arrays.copyOf(board[i], board[i].length);
        }
    }

    /**
     * Fills board at col with piece
     * @param col   column to fill
     * @param piece the piece
     * @return if the fill is possible or not
     */
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

    /**
     * return the board
     * @return board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * returns the deep copy of board
     * @return
     */
    public char[][] getBoardDeepCopy() {
        char[][] boardCopy = new char[6][];
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return boardCopy;
    }

    /**
     * Checks if board is full
     * @return if board is full
     */
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

    /**
     * Checks if a move in a column is possible
     * @param col column of the move
     * @return if the move is possible
     */
    public boolean isPossibleMove(int col) {
        return board[0][col] == '\0';
    }

    /**
     * Checks if there are 4 consecutive pieces Horizontally, vertically and diagonally
     * @param piece the piece
     * @return if there are 4 consecutive pieces
     */
    public boolean checkWinner(char piece) {
        return checkHorizontal(piece) || checkVertical(piece) || checkUpWardDiag(piece) || checkDownWardDiag(piece);
    }
    /**
     * Checks if there are 4 consecutive pieces Horizontally, vertically and diagonally
     * @return if there are 4 consecutive pieces
     */
    public boolean checkWinner() {
        return checkWinner('x') ||checkWinner('o');
    }

    /**
     * Checks if there are 4 consecutive pieces Horizontally
     * @param piece
     * @return if there are 4 consecutive pieces Horizontally
     */
    private boolean checkHorizontal(char piece) {
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
    /**
     * Checks if there are 4 consecutive pieces vertically
     * @param piece
     * @return if there are 4 consecutive pieces vertically
     */
    private boolean checkVertical(char piece) {
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
    /**
     * Checks if there are 4 consecutive pieces on the upward diagonals
     * @param piece
     * @return if there are 4 consecutive pieces on the upward diagonals
     */
    private boolean checkUpWardDiag(char piece) {
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
    /**
     * Checks if there are 4 consecutive pieces on the downward diagonals
     * @param piece
     * @return if there are 4 consecutive pieces on the downward diagonals
     */
    private boolean checkDownWardDiag(char piece) {
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

    /**
     * Returns a text representation of board
     * @return text representation
     */
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

    /**
     * Generates all the possible moves
     * @return possible moves
     */
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> possibleMoves = new ArrayList();

        for (int i = 0; i < board[0].length; i++) {
            if (isPossibleMove(i)) {
                possibleMoves.add(i);
            }
        }

        return possibleMoves;
    }

    /**
     * Returns the maximum number of consecutive pieces in the board
     * @param piece the piece
     * @return maximum number of consecutive pieces
     */
    public int maxConsecutivePieces(char piece) {
        return Math.max(Math.max(maxHorizontalConsecutivePieces(piece), maxVerticalConsecutivePieces(piece)), Math.max(maxDownWardDiagConsecutivePieces(piece), maxUpWardDiagConsecutivePieces(piece)));
    }

    /**
     * Returns the maximum number of horizontal consecutive pieces
     *
     * @param piece the piece
     * @return maximum number of consecutive pieces
     */
    private int maxHorizontalConsecutivePieces(char piece) {
        int count = 0, maxConsecutivePieces = 0;
        for (int c = 0; c < board[0].length; c++) {
            for (char[] row : board) {
                if (row[c] == piece) count++;
                else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
            }
        }
        return maxConsecutivePieces;
    }

    /**
     * Returns the maximum number of vertical consecutive pieces
     *
     * @param piece the piece
     * @return maximum number of consecutive pieces
     */
    private int maxVerticalConsecutivePieces(char piece) {
        int count = 0, maxConsecutivePieces = 0;
        for (int c = 0; c < board[0].length; c++) {
            for (int r = 2; r < board.length; r++) {
                if (board[r][c] == piece) count++;
                else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
            }
        }
        return maxConsecutivePieces;
    }

    /**
     * Returns the maximum number of consecutive pieces in the downward diagonals
     *
     * @param piece the piece
     * @return maximum number of consecutive pieces
     */
    private int maxDownWardDiagConsecutivePieces(char piece) {
        int count = 0, maxConsecutivePieces = 0;
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row + 1][col + 1] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row + 2][col + 2] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row + 3][col + 3] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
            }
        }
        return maxConsecutivePieces;
    }

    /**
     * Returns the maximum number of consecutive pieces in the upward diagonals
     *
     * @param piece the piece
     * @return maximum number of consecutive pieces
     */
    private int maxUpWardDiagConsecutivePieces(char piece) {
        int count = 0, maxConsecutivePieces = 0;
        for (int row = 3; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row - 1][col + 1] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row - 2][col + 2] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
                if (board[row - 3][col + 3] == piece) {
                    count++;
                } else if (count != 0 && count > maxConsecutivePieces) {
                    maxConsecutivePieces = count;
                    count = 0;
                }
            }
        }
        return maxConsecutivePieces;
    }

}
