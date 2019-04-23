
/**
 *
 * @author mouhieddine
 */
import java.util.ArrayList;

public class Engine {

    public int getBestMoveIndex(Board state, int depth ,int alpha, int beta, boolean player) {
        int maxValue=-1, index=0, currentValue;
        ArrayList<Integer> movesScore= new ArrayList();
        if (depth <= 0 || isTerminalNode(state)) {
            return score(state);
        }
        for (int i : state.getPossibleMoves()) {
             Board newState = new Board(state.getBoardDeepCopy());
             newState.fill(i,'x');
             currentValue=alpha_beta(newState, depth,alpha, beta, player);

             if(currentValue>maxValue) {maxValue=currentValue; index= i;}

        }
        return index;
    }

    public int alpha_beta(Board state,int depth, int alpha, int beta, boolean player) {
        int value;
        if (depth <= 0 || isTerminalNode(state)) {

            return score(state);
        }
        else if (player) {
            value = Integer.MIN_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'x');

                value = Math.max(value, alpha_beta(newState,depth-1,alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (alpha>=beta) return value;
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'o');
                value = Math.min(value, alpha_beta(newState,depth-1, alpha, beta, true));
                beta = Math.min(beta, value);
                if (alpha>=beta) return value;

            }
            return value;
        }

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

            return scoreOf(state);
        }
    }
    private int maxValueOf(ArrayList<Integer> list){
        int max=list.get(0);
        for(int i: list){
            if(i>max) max=i;
        }
        return max;
    }

    /**
     *
     * @param state
     * @return
     */
    public int scoreOf(Board state){
        int value =0, count=0;
        ArrayList<Integer> counts= new ArrayList();
        // rows
        for( int c=0; c< state.getBoard()[0].length;c++){
            for( char[] row: state.getBoard()){
                if(row[c]=='x') count++;
                else if(count!=0){
                counts.add(count);
                count=0;
                }
            }
        }

        counts.add(count);
        value=maxValueOf(counts);
        counts.clear();
        counts.add(0);
        count=0;
        //cols
        for( int c=0; c<state.getBoard()[0].length;c++){
            for(int r=2; r<state.getBoard().length;r++){
                if(state.getBoard()[r][c]=='x') count++;
                else if(count!=0){
                    counts.add(count);
                    count=0;
                }
            }
        }
        counts.add(count);
        value = maxValueOf(counts);
        counts.clear();
        counts.add(0);
        count = 0;
        //DownWard diag
        for (int row = 0; row < state.getBoard().length - 3; row++) {
            for (int col = 0; col < state.getBoard()[0].length - 3; col++) {
                if (state.getBoard()[row][col] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row + 1][col + 1] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row + 2][col + 2] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row + 3][col + 3] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
            }
        }
        counts.add(count);
        value = maxValueOf(counts);
        counts.clear();
        counts.add(0);
        count = 0;
        for (int row = 3; row < state.getBoard().length; row++) {
            for (int col = 0; col < state.getBoard()[0].length - 3; col++) {
                if (state.getBoard()[row][col] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row - 1][col + 1] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row - 2][col + 2] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
                if (state.getBoard()[row - 3][col + 3] == 'x') {
                    count++;
                } else if (count != 0) {
                    counts.add(count);
                    count = 0;
                }
            }
        }

        return Math.max(value,maxValueOf(counts));
    }
}
