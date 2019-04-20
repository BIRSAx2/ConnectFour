
/**
 *
 * @author mouhieddine
 */
import java.util.ArrayList;

public class Engine {

    public int getBestMoveIndex(Board state, int depth, int alpha, int beta, boolean player) {
        int value;
        ArrayList<Integer> movesScore= new ArrayList();
        
        for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                movesScore.add(alpha_beta(newState, depth - 1, alpha, beta, true));
        }
        return state.getPossibleMoves().get(movesScore.indexOf(maxValueOf(movesScore)));
    }

    public int alpha_beta(Board state, int depth, int alpha, int beta, boolean player) {
        int value;
        if (depth == 0 || isTerminalNode(state)) {
            return score(state);
        }
        if (player) {
            value = Integer.MIN_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'x');
                value = Math.max(value, alpha_beta(newState, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    return value;

                }
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'o');
                value = Math.min(value, alpha_beta(newState, depth - 1, alpha, beta, false));
                alpha = Math.min(alpha, value);
                if (beta <= alpha) {
                    return value;

                }
                return value;
            }
        }
        return 0;

    }

    private boolean isTerminalNode(Board state) {
        return (state.isBoardFull() || state.checkWinner());
    }

    private int score(Board state) {
        if (state.checkWinner('x')) {
            return 10;
        } else if (state.checkWinner('o')) {
            return -10;
        } else {
            return 0;
        }
    }
    private int maxValueOf(ArrayList<Integer> list){
        int max=list.get(0);
        for(int i: list){
            if(i>max) max=i;
        }
        return max;
    }
}
