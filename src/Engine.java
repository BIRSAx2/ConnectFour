
/**
 *
 * @author mouhieddine
 */

public class Engine {
    private char AIPiece;
    private char opponentPiece;

    /**
     * Constructor with no param
     */
    public Engine() {
    }

    /**
     * Constructor that sets the AIPiece
     *
     * @param AIPiece 'x' or 'o'
     */
    public Engine(char AIPiece) {
        AIPiece = Character.toLowerCase(AIPiece);
        if (AIPiece == 'x' || AIPiece == 'o') {
            this.AIPiece = AIPiece;
            this.opponentPiece = (AIPiece == 'x') ? 'o' : 'x';
        }
    }

    /**
     * Return AI piece
     *
     * @return
     */
    public char getAIPiece() {
        return AIPiece;
    }

    /**
     * Sets the AIPiece
     *
     * @param AIPiece new AI piece
     */
    public void setAIPiece(char AIPiece) {
        AIPiece = Character.toLowerCase(AIPiece);
        if (AIPiece == 'x' || AIPiece == 'o') {
            this.AIPiece = AIPiece;
            this.opponentPiece = (AIPiece == 'x') ? 'o' : 'x';
        }
    }

    /**
     * Return the best move from a given state
     *
     * @param state  actual state of the game
     * @param depth  depth of search of the best move
     * @param alpha  best value for AI
     * @param beta   best value for opponent
     * @param player actual player, True: AI, False: Opponent
     * @return
     */
    public int getBestMoveIndex(Board state, int depth, int alpha, int beta, boolean player) {
        int maxScore = -1, index = 0, currentScore;
        if (depth <= 0 || isTerminalNode(state)) {
            return score(state);
        }
        for (int i : state.getPossibleMoves()) {
            Board newState = new Board(state.getBoardDeepCopy());
            newState.fill(i, getAIPiece());
            currentScore = alpha_beta(newState, depth, alpha, beta, player);

            if (currentScore > maxScore) {
                maxScore = currentScore;
                index = i;
            }
        }
        return index;
    }

    /**
     * Return the score of a move from a given state
     *
     * @param state  actual state of the game
     * @param depth  depth of search of the best move
     * @param alpha  best value for AI
     * @param beta   best value for opponent
     * @param player actual player, True: AI, False: Opponent
     * @return
     */
    public int alpha_beta(Board state, int depth, int alpha, int beta, boolean player) {
        int value;
        if (depth <= 0 || isTerminalNode(state)) {

            return score(state);
        } else if (player) {
            value = Integer.MIN_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, getAIPiece());

                value = Math.max(value, alpha_beta(newState, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (alpha >= beta) return value;
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, opponentPiece);
                value = Math.min(value, alpha_beta(newState, depth - 1, alpha, beta, true));
                beta = Math.min(beta, value);
                if (alpha >= beta) return value;

            }
            return value;
        }

    }

    /**
     * Checks if a state is a terminal state
     * @param state
     * @return if it is a terminal state
     */
    private boolean isTerminalNode(Board state) {
        return (state.isBoardFull() || state.checkWinner());
    }

    /**
     * Returns the AI's score from a given state
     * @param state
     * @return
     */
    private int score(Board state) {
        if (state.checkWinner(getAIPiece())) {

            return 10;
        } else if (state.checkWinner(opponentPiece)) {
            return -10;
        } else {
            return state.maxConsecutivePieces(getAIPiece());
        }
    }
}
