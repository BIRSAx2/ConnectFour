
/**
 *
 * @author mouhieddine
 */
import java.util.ArrayList;

public class Engine {

    public int getBestMoveIndex(Board state, int depth ,int alpha, int beta, boolean player) {
        int value=0, index=0;
        ArrayList<Integer> movesScore= new ArrayList();
        char piece= player ? 'x' :'o';
        for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i,piece);
                if(alpha_beta(newState, depth,alpha, beta, player)>value) {value=alpha_beta(newState, depth,alpha, beta, player); index= i;}
            if(alpha_beta(newState, depth,alpha, beta, player)>=value) System.out.println("Move "+i+" Score " +value);
                movesScore.add(alpha_beta(newState, depth,alpha, beta, player));
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
                value = Math.min(value, alpha_beta(newState,depth-1, alpha, beta, true));
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    return value;

                }
                return value;
            }
        }
        return score(state);

    }

    private boolean isTerminalNode(Board state) {
        return (state.isBoardFull() || state.checkWinner());
    }

    private int score(Board state) {
        if (state.checkWinner('x')) {
            System.out.println(state);
            return 1000;
        } else if (state.checkWinner('o')) {
            return -1000;
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
                else{
                counts.add(count);
                count=0;
                }
            }
        }
        counts.add(count);
        value=maxValueOf(counts);
        counts.clear();
        count=0;
        //cols
        for( int c=0; c<state.getBoard()[0].length;c++){
            for(int r=0; r<state.getBoard().length;r++){
                if(state.getBoard()[r][c]=='x') count++;
                else{
                    counts.add(count);
                    count=0;
                }
            }
        }

        return Math.max(value,maxValueOf(counts));
    }
}
